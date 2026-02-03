using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;

namespace bai5.Controllers;

public class Bai5Controller : Controller
{
    private readonly string _cn;
    public Bai5Controller(IConfiguration config) => _cn = config.GetConnectionString("DefaultConnection")!;

    [HttpGet]
    public IActionResult AddOrgUnit() => View();

    [HttpPost]
    public IActionResult AddOrgUnit(string? unitId, string? name, string? description)
    {
        var id = (unitId ?? "").Trim();
        var nm = (name ?? "").Trim();
        var ds = (description ?? "").Trim();

        var errors = new List<string>();
        if (id.Length == 0) errors.Add("Unit Id không được rỗng.");
        if (id.Length > 50) errors.Add("Unit Id không vượt quá 50 ký tự.");
        if (nm.Length == 0) errors.Add("Name không được rỗng.");
        if (nm.Length > 100) errors.Add("Name không vượt quá 100 ký tự.");
        if (ds.Length > 400) errors.Add("Description không vượt quá 400 ký tự.");

        if (errors.Count > 0)
        {
            ViewBag.Errors = errors;
            return View();
        }

        try
        {
            using var cn = new SqlConnection(_cn);
            cn.Open();
            using var cmd = new SqlCommand(@"
INSERT INTO OrgUnit(UnitId, Name, Description)
VALUES (@UnitId, @Name, @Desc);", cn);

            cmd.Parameters.AddWithValue("@UnitId", id);
            cmd.Parameters.AddWithValue("@Name", nm);
            cmd.Parameters.AddWithValue("@Desc", string.IsNullOrWhiteSpace(ds) ? (object)DBNull.Value : ds);

            cmd.ExecuteNonQuery();
            ViewBag.Ok = "Save Organization Unit thành công!";
            return View();
        }
        catch (Exception ex)
        {
            ViewBag.Errors = new List<string> { ex.Message };
            return View();
        }
    }
}
