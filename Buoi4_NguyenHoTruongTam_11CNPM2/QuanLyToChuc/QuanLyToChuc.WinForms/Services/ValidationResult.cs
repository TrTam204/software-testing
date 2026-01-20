namespace QuanLyToChuc.WinForms.Services;

public class ValidationResult
{
    public bool IsValid { get; set; }
    public string? Message { get; set; }
    public string? TargetField { get; set; } // Add this field

    public static ValidationResult Success() => new ValidationResult { IsValid = true };
    public static ValidationResult Fail(string message, string? targetField = null) => 
        new ValidationResult { IsValid = false, Message = message, TargetField = targetField };
}
