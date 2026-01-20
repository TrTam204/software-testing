namespace QuanLyToChuc.WinForms.Forms;

public partial class DirectorForm : Form
{
    public DirectorForm(int orgId, string orgName)
    {
        InitializeComponent();
        lblInfo.Text = $"Organization ID: {orgId}, Name: {orgName}";
    }
}
