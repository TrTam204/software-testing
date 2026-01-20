using Microsoft.Data.SqlClient;
using QuanLyToChuc.WinForms.Models;
using System.Data;

namespace QuanLyToChuc.WinForms.Data;

public class OrganizationRepository
{
    public bool ExistsByName(string orgName)
    {
        using var conn = new SqlConnection(DbConfig.ConnectionString);
        conn.Open();
        using var cmd = new SqlCommand("SELECT COUNT(1) FROM dbo.Organization WHERE UPPER(OrgName) = UPPER(@name)", conn);
        // Trim input and set size
        cmd.Parameters.Add("@name", SqlDbType.NVarChar, 255).Value = orgName.Trim();
        int count = (int)cmd.ExecuteScalar();
        return count > 0;
    }

    public int Insert(Organization org)
    {
        using var conn = new SqlConnection(DbConfig.ConnectionString);
        conn.Open();
        // Using SCOPE_IDENTITY() as requested
        string sql = @"
            INSERT INTO dbo.Organization (OrgName, Address, Phone, Email)
            VALUES (@OrgName, @Address, @Phone, @Email);
            SELECT CAST(SCOPE_IDENTITY() as int);";
        
        using var cmd = new SqlCommand(sql, conn);
        // Trim inputs and set sizes
        cmd.Parameters.Add("@OrgName", SqlDbType.NVarChar, 255).Value = org.OrgName.Trim();
        cmd.Parameters.Add("@Address", SqlDbType.NVarChar, 255).Value = (object?)org.Address?.Trim() ?? DBNull.Value;
        cmd.Parameters.Add("@Phone", SqlDbType.VarChar, 12).Value = (object?)org.Phone?.Trim() ?? DBNull.Value;
        cmd.Parameters.Add("@Email", SqlDbType.VarChar, 255).Value = (object?)org.Email?.Trim() ?? DBNull.Value;

        return (int)cmd.ExecuteScalar();
    }
}
