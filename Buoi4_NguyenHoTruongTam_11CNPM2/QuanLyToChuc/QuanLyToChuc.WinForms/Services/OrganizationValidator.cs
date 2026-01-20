using QuanLyToChuc.WinForms.Models;
using System.Text.RegularExpressions;

namespace QuanLyToChuc.WinForms.Services;

public class OrganizationValidator
{
    public ValidationResult Validate(Organization org)
    {
        // OrgName: Required, 3-255 chars
        if (string.IsNullOrWhiteSpace(org.OrgName))
            return ValidationResult.Fail("Organization Name is required.", nameof(Organization.OrgName));
        
        if (org.OrgName.Length < 3 || org.OrgName.Length > 255)
            return ValidationResult.Fail("Organization Name must be between 3 and 255 characters.", nameof(Organization.OrgName));

        // Email: Format check if provided
        if (!string.IsNullOrEmpty(org.Email))
        {
             // Simple regex for email
             var emailRegex = new Regex(@"^[^@\s]+@[^@\s]+\.[^@\s]+$");
             if (!emailRegex.IsMatch(org.Email))
                return ValidationResult.Fail("Invalid Email format.", nameof(Organization.Email));
        }

        // Phone: Digits only, 9-12 chars if provided
        if (!string.IsNullOrEmpty(org.Phone))
        {
            var phoneRegex = new Regex(@"^[0-9]{9,12}$");
            if (!phoneRegex.IsMatch(org.Phone))
                return ValidationResult.Fail("Phone must be 9-12 digits.", nameof(Organization.Phone));
        }

        return ValidationResult.Success();
    }
}
