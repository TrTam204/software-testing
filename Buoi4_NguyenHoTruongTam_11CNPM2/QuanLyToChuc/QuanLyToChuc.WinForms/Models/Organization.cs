namespace QuanLyToChuc.WinForms.Models;

public class Organization
{
    public int OrgID { get; set; }
    public string OrgName { get; set; } = string.Empty;
    public string? Address { get; set; }
    public string? Phone { get; set; }
    public string? Email { get; set; }
    public DateTime CreatedDate { get; set; }
}
