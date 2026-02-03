using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Text.RegularExpressions;

namespace bai6.Controllers;

public class Bai6Controller : Controller
{
    private readonly string _cn;
    public Bai6Controller(IConfiguration config) => _cn = config.GetConnectionString("DefaultConnection")!;

    [HttpGet]
    public IActionResult Index(string? username)
    {
        ViewBag.Users = GetAll();

        if (!string.IsNullOrWhiteSpace(username))
        {
            ViewBag.EditUser = GetByUsername(username.Trim());
        }

        return View();
    }

    [HttpPost]
    public IActionResult Create(string? username, string? fullname, string? email, string? password)
    {
        var u = (username ?? "").Trim();
        var f = (fullname ?? "").Trim();
        var e = (email ?? "").Trim();
        var p = (password ?? "").Trim();

        var errors = Validate(u, f, e, p);
        if (errors.Count > 0)
        {
            ViewBag.Errors = errors;
            ViewBag.Users = GetAll();
            return View("Index");
        }

        try
        {
            using var cn = new SqlConnection(_cn);
            cn.Open();
            using var cmd = new SqlCommand(@"
INSERT INTO AppUser(Username, Email, Role, Status, PasswordHash, Fullname)
VALUES (@U, @E, N'employee', N'active', @P, @F);", cn);

            cmd.Parameters.AddWithValue("@U", u);
            cmd.Parameters.AddWithValue("@E", string.IsNullOrWhiteSpace(e) ? (object)DBNull.Value : e);
            cmd.Parameters.AddWithValue("@P", p);
            cmd.Parameters.AddWithValue("@F", string.IsNullOrWhiteSpace(f) ? (object)DBNull.Value : f);

            cmd.ExecuteNonQuery();
            return RedirectToAction("Index", new { username = u });
        }
        catch (Exception ex)
        {
            ViewBag.Errors = new List<string> { ex.Message };
            ViewBag.Users = GetAll();
            return View("Index");
        }
    }

    [HttpPost]
    public IActionResult Update(string? username, string? fullname, string? email, string? password)
    {
        var u = (username ?? "").Trim();
        var f = (fullname ?? "").Trim();
        var e = (email ?? "").Trim();
        var p = (password ?? "").Trim();

        var errors = Validate(u, f, e, p);
        if (errors.Count > 0)
        {
            ViewBag.Errors = errors;
            ViewBag.Users = GetAll();
            ViewBag.EditUser = GetByUsername(u);
            return View("Index");
        }

        try
        {
            using var cn = new SqlConnection(_cn);
            cn.Open();
            using var cmd = new SqlCommand(@"
UPDATE AppUser
SET Email=@E, PasswordHash=@P, Fullname=@F
WHERE Username=@U;", cn);

            cmd.Parameters.AddWithValue("@U", u);
            cmd.Parameters.AddWithValue("@E", string.IsNullOrWhiteSpace(e) ? (object)DBNull.Value : e);
            cmd.Parameters.AddWithValue("@P", p);
            cmd.Parameters.AddWithValue("@F", string.IsNullOrWhiteSpace(f) ? (object)DBNull.Value : f);

            var rows = cmd.ExecuteNonQuery();
            if (rows == 0) throw new Exception("Không tìm thấy user để Update.");

            return RedirectToAction("Index", new { username = u });
        }
        catch (Exception ex)
        {
            ViewBag.Errors = new List<string> { ex.Message };
            ViewBag.Users = GetAll();
            ViewBag.EditUser = GetByUsername(u);
            return View("Index");
        }
    }

    [HttpPost]
    public IActionResult Delete(string? username)
    {
        var u = (username ?? "").Trim();
        if (string.IsNullOrWhiteSpace(u))
            return RedirectToAction("Index");

        try
        {
            using var cn = new SqlConnection(_cn);
            cn.Open();
            using var cmd = new SqlCommand("DELETE FROM AppUser WHERE Username=@U;", cn);
            cmd.Parameters.AddWithValue("@U", u);
            cmd.ExecuteNonQuery();
        }
        catch { /* ignore */ }

        return RedirectToAction("Index");
    }

    // ===== DB helpers =====
    private List<UserRow> GetAll()
    {
        using var cn = new SqlConnection(_cn);
        cn.Open();
        using var cmd = new SqlCommand(@"
SELECT Username, Fullname, Email
FROM AppUser
ORDER BY Username;", cn);

        using var r = cmd.ExecuteReader();
        var list = new List<UserRow>();
        while (r.Read())
        {
            list.Add(new UserRow(
                r.GetString(0),
                r.IsDBNull(1) ? "" : r.GetString(1),
                r.IsDBNull(2) ? "" : r.GetString(2)
            ));
        }
        return list;
    }

    private UserRow? GetByUsername(string username)
    {
        using var cn = new SqlConnection(_cn);
        cn.Open();
        using var cmd = new SqlCommand(@"
SELECT Username, Fullname, Email
FROM AppUser
WHERE Username=@U;", cn);
        cmd.Parameters.AddWithValue("@U", username);

        using var r = cmd.ExecuteReader();
        if (!r.Read()) return null;

        return new UserRow(
            r.GetString(0),
            r.IsDBNull(1) ? "" : r.GetString(1),
            r.IsDBNull(2) ? "" : r.GetString(2)
        );
    }

    private List<string> Validate(string u, string f, string e, string p)
    {
        var errors = new List<string>();

        // Username
        if (u.Length == 0) errors.Add("Username không được rỗng.");
        if (u.Length > 50) errors.Add("Username không vượt quá 50 ký tự.");

        // Fullname (cho giống ảnh)
        if (f.Length == 0) errors.Add("Fullname không được rỗng.");
        if (f.Length > 100) errors.Add("Fullname không vượt quá 100 ký tự.");

        // Email
        if (!string.IsNullOrWhiteSpace(e))
        {
            if (e.Length > 120) errors.Add("Email không vượt quá 120 ký tự.");
            var ok = Regex.IsMatch(e, @"^[^@\s]+@[^@\s]+\.[^@\s]+$");
            if (!ok) errors.Add("Email không đúng định dạng.");
        }

        // Password
        if (p.Length == 0) errors.Add("Password không được rỗng.");
        if (p.Length > 200) errors.Add("Password không vượt quá 200 ký tự.");

        return errors;
    }

    public record UserRow(string Username, string Fullname, string Email);
}
