using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;

namespace bai4.Controllers;

public class Bai4Controller : Controller
{
    private readonly string _cn;

    public Bai4Controller(IConfiguration config)
    {
        _cn = config.GetConnectionString("DefaultConnection")!;
    }

    [HttpGet]
    public IActionResult AddJobTitle() => View();

    [HttpPost]
    public IActionResult AddJobTitle(string? title, string? description, string? note, IFormFile? jobSpec)
    {
        var t = (title ?? "").Trim();
        var d = (description ?? "").Trim();
        var n = (note ?? "").Trim();

        // Validate theo đề: Title 1..100; Desc <=400; Note <=400; File <=1024KB
        var errors = new List<string>();
        if (t.Length == 0) errors.Add("Job Title không được rỗng.");
        if (t.Length > 100) errors.Add("Job Title không vượt quá 100 ký tự.");
        if (d.Length > 400) errors.Add("Description không vượt quá 400 ký tự.");
        if (n.Length > 400) errors.Add("Note không vượt quá 400 ký tự.");

        int fileSizeKb = 0;
        byte[]? fileBytes = null;
        string? fileName = null;

        if (jobSpec != null && jobSpec.Length > 0)
        {
            fileName = jobSpec.FileName;
            fileSizeKb = (int)Math.Ceiling(jobSpec.Length / 1024.0);

            if (fileSizeKb > 1024) errors.Add("Job Specification không vượt quá 1024KB.");

            using var ms = new MemoryStream();
            jobSpec.CopyTo(ms);
            fileBytes = ms.ToArray();
        }

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
INSERT INTO JobTitle(Title, Description, JobSpecFileName, JobSpecFileBytes, JobSpecFileSizeKB, Note)
VALUES (@Title, @Desc, @FileName, @FileBytes, @FileSizeKB, @Note);", cn);

            cmd.Parameters.AddWithValue("@Title", t);
            cmd.Parameters.AddWithValue("@Desc", string.IsNullOrWhiteSpace(d) ? (object)DBNull.Value : d);
            cmd.Parameters.AddWithValue("@FileName", (object?)fileName ?? DBNull.Value);
            cmd.Parameters.AddWithValue("@FileBytes", (object?)fileBytes ?? DBNull.Value);
            cmd.Parameters.AddWithValue("@FileSizeKB", fileName == null ? (object)DBNull.Value : fileSizeKb);
            cmd.Parameters.AddWithValue("@Note", string.IsNullOrWhiteSpace(n) ? (object)DBNull.Value : n);

            cmd.ExecuteNonQuery();

            ViewBag.Ok = "Save Job Title thành công!";
            return View();
        }
        catch (Exception ex)
        {
            ViewBag.Errors = new List<string> { ex.Message };
            return View();
        }
    }
}
