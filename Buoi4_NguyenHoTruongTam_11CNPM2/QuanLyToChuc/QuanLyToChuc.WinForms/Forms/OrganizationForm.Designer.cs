namespace QuanLyToChuc.WinForms.Forms;

partial class OrganizationForm
{
    private System.ComponentModel.IContainer components = null;
    private System.Windows.Forms.TextBox txtOrgName;
    private System.Windows.Forms.TextBox txtAddress;
    private System.Windows.Forms.TextBox txtPhone;
    private System.Windows.Forms.TextBox txtEmail;
    private System.Windows.Forms.Label lblOrgName;
    private System.Windows.Forms.Label lblAddress;
    private System.Windows.Forms.Label lblPhone;
    private System.Windows.Forms.Label lblEmail;
    private System.Windows.Forms.Button btnSave;
    private System.Windows.Forms.Button btnBack;
    private System.Windows.Forms.Button btnDirector;
    private System.Windows.Forms.ErrorProvider errorProvider1;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
        {
            components.Dispose();
        }
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        this.components = new System.ComponentModel.Container();
        this.txtOrgName = new System.Windows.Forms.TextBox();
        this.txtAddress = new System.Windows.Forms.TextBox();
        this.txtPhone = new System.Windows.Forms.TextBox();
        this.txtEmail = new System.Windows.Forms.TextBox();
        this.lblOrgName = new System.Windows.Forms.Label();
        this.lblAddress = new System.Windows.Forms.Label();
        this.lblPhone = new System.Windows.Forms.Label();
        this.lblEmail = new System.Windows.Forms.Label();
        this.btnSave = new System.Windows.Forms.Button();
        this.btnBack = new System.Windows.Forms.Button();
        this.btnDirector = new System.Windows.Forms.Button();
        this.errorProvider1 = new System.Windows.Forms.ErrorProvider(this.components);
        ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).BeginInit();
        this.SuspendLayout();
        
        // Labels
        int startX = 30;
        int startY = 30;
        int gapY = 40;
        
        this.lblOrgName.AutoSize = true;
        this.lblOrgName.Location = new System.Drawing.Point(startX, startY);
        this.lblOrgName.Text = "Org Name:";
        
        this.lblAddress.AutoSize = true;
        this.lblAddress.Location = new System.Drawing.Point(startX, startY + gapY);
        this.lblAddress.Text = "Address:";

        this.lblPhone.AutoSize = true;
        this.lblPhone.Location = new System.Drawing.Point(startX, startY + gapY * 2);
        this.lblPhone.Text = "Phone:";

        this.lblEmail.AutoSize = true;
        this.lblEmail.Location = new System.Drawing.Point(startX, startY + gapY * 3);
        this.lblEmail.Text = "Email:";

        // TextBoxes
        int txtX = 120;
        int txtWidth = 200;
        
        this.txtOrgName.Location = new System.Drawing.Point(txtX, startY - 3);
        this.txtOrgName.Name = "txtOrgName";
        this.txtOrgName.Size = new System.Drawing.Size(txtWidth, 23);
        this.txtOrgName.MaxLength = 300;

        this.txtAddress.Location = new System.Drawing.Point(txtX, startY + gapY - 3);
        this.txtAddress.Name = "txtAddress";
        this.txtAddress.Size = new System.Drawing.Size(txtWidth, 23);
        this.txtAddress.MaxLength = 300;

        this.txtPhone.Location = new System.Drawing.Point(txtX, startY + gapY * 2 - 3);
        this.txtPhone.Name = "txtPhone";
        this.txtPhone.Size = new System.Drawing.Size(txtWidth, 23);
        this.txtPhone.MaxLength = 15;

        this.txtEmail.Location = new System.Drawing.Point(txtX, startY + gapY * 3 - 3);
        this.txtEmail.Name = "txtEmail";
        this.txtEmail.Size = new System.Drawing.Size(txtWidth, 23);
        this.txtEmail.MaxLength = 300;

        // Buttons
        int btnY = startY + gapY * 4 + 10;
        
        this.btnSave.Location = new System.Drawing.Point(startX, btnY);
        this.btnSave.Name = "btnSave";
        this.btnSave.Size = new System.Drawing.Size(75, 30);
        this.btnSave.Text = "Save";
        this.btnSave.Click += new System.EventHandler(this.btnSave_Click);

        this.btnBack.Location = new System.Drawing.Point(startX + 90, btnY);
        this.btnBack.Name = "btnBack";
        this.btnBack.Size = new System.Drawing.Size(75, 30);
        this.btnBack.Text = "Back";
        this.btnBack.Click += new System.EventHandler(this.btnBack_Click);

        this.btnDirector.Location = new System.Drawing.Point(startX + 180, btnY);
        this.btnDirector.Name = "btnDirector";
        this.btnDirector.Size = new System.Drawing.Size(75, 30);
        this.btnDirector.Text = "Director";
        this.btnDirector.Click += new System.EventHandler(this.btnDirector_Click);

        // ErrorProvider
        this.errorProvider1.ContainerControl = this;

        // Form
        this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(400, 300);
        this.Controls.Add(this.lblOrgName);
        this.Controls.Add(this.txtOrgName);
        this.Controls.Add(this.lblAddress);
        this.Controls.Add(this.txtAddress);
        this.Controls.Add(this.lblPhone);
        this.Controls.Add(this.txtPhone);
        this.Controls.Add(this.lblEmail);
        this.Controls.Add(this.txtEmail);
        this.Controls.Add(this.btnSave);
        this.Controls.Add(this.btnBack);
        this.Controls.Add(this.btnDirector);
        this.Name = "OrganizationForm";
        this.Text = "Organization Form";
        ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).EndInit();
        this.ResumeLayout(false);
        this.PerformLayout();
    }
}
