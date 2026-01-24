namespace QuanLyToChuc.WinForms.Forms;

public partial class MainForm : Form
{
    public MainForm()
    {
        InitializeComponent();
    }

    private void btnOpenOrgForm_Click(object sender, EventArgs e)
    {
        // Hide Main Form and Open Organization Form
        var orgForm = new OrganizationForm(this);
        this.Hide();
        orgForm.Show();
    }
}
