using Microsoft.Data.SqlClient;
using QuanLyToChuc.WinForms.Data;
using QuanLyToChuc.WinForms.Models;
using QuanLyToChuc.WinForms.Services;

namespace QuanLyToChuc.WinForms.Forms;

public partial class OrganizationForm : Form
{
    private readonly OrganizationRepository _repository;
    private readonly OrganizationValidator _validator;
    private int? _savedOrgId = null; // Changed to nullable
    private string _savedOrgName = string.Empty;
    private readonly Form? _parentForm; // Store parent form reference

    // Default constructor for direct run or when no parent
    public OrganizationForm() : this(null)
    {
    }

    // Constructor accepting parent form
    public OrganizationForm(Form? parentForm)
    {
        InitializeComponent();
        _repository = new OrganizationRepository();
        _validator = new OrganizationValidator();
        _parentForm = parentForm;
        
        // Initial state
        btnDirector.Enabled = false;
    }

    private void btnSave_Click(object sender, EventArgs e)
    {
        // Clear errors
        errorProvider1.Clear();

        // 1. Map UI to Model
        var org = new Organization
        {
            OrgName = txtOrgName.Text.Trim(),
            Address = txtAddress.Text.Trim(),
            Phone = txtPhone.Text.Trim(),
            Email = txtEmail.Text.Trim()
        };

        // 2. Validate
        var result = _validator.Validate(org);
        if (!result.IsValid)
        {
            // Optional: Set ErrorProvider
            if (result.TargetField == nameof(Organization.OrgName)) errorProvider1.SetError(txtOrgName, result.Message);
            else if (result.TargetField == nameof(Organization.Email)) errorProvider1.SetError(txtEmail, result.Message);
            else if (result.TargetField == nameof(Organization.Phone)) errorProvider1.SetError(txtPhone, result.Message);
            
            MessageBox.Show(result.Message, "Validation Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            return;
        }

        // 3. Check Duplicate & Insert
        try
        {
            if (_repository.ExistsByName(org.OrgName))
            {
                MessageBox.Show("Organization Name already exists", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            // 4. Insert
            int newId = _repository.Insert(org);
            _savedOrgId = newId;
            _savedOrgName = org.OrgName;

            // 5. Success
            MessageBox.Show("Save successfully", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
            
            // 6. Enable Director button
            btnDirector.Enabled = true;
        }
        catch (SqlException ex) when (ex.Number == 2601 || ex.Number == 2627)
        {
             MessageBox.Show("Organization Name already exists", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void btnBack_Click(object sender, EventArgs e)
    {
        this.Close();
        _parentForm?.Show(); // Show parent form if it exists
    }

    private void btnDirector_Click(object sender, EventArgs e)
    {
        if (_savedOrgId == null)
        {
            MessageBox.Show("Please save first", "Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            return;
        }
        
        var form = new DirectorForm(_savedOrgId.Value, _savedOrgName);
        form.ShowDialog();
    }
}
