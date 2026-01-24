namespace QuanLyToChuc.WinForms.Forms;

partial class MainForm
{
    private System.ComponentModel.IContainer components = null;
    private System.Windows.Forms.Button btnOpenOrgForm;
    private System.Windows.Forms.Label lblTitle;

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
        this.btnOpenOrgForm = new System.Windows.Forms.Button();
        this.lblTitle = new System.Windows.Forms.Label();
        this.SuspendLayout();
        // 
        // btnOpenOrgForm
        // 
        this.btnOpenOrgForm.Location = new System.Drawing.Point(100, 100);
        this.btnOpenOrgForm.Name = "btnOpenOrgForm";
        this.btnOpenOrgForm.Size = new System.Drawing.Size(200, 50);
        this.btnOpenOrgForm.TabIndex = 0;
        this.btnOpenOrgForm.Text = "Open Organization Form";
        this.btnOpenOrgForm.UseVisualStyleBackColor = true;
        this.btnOpenOrgForm.Click += new System.EventHandler(this.btnOpenOrgForm_Click);
        // 
        // lblTitle
        // 
        this.lblTitle.AutoSize = true;
        this.lblTitle.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
        this.lblTitle.Location = new System.Drawing.Point(120, 40);
        this.lblTitle.Name = "lblTitle";
        this.lblTitle.Size = new System.Drawing.Size(160, 25);
        this.lblTitle.TabIndex = 1;
        this.lblTitle.Text = "Main Application";
        // 
        // MainForm
        // 
        this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(400, 300);
        this.Controls.Add(this.lblTitle);
        this.Controls.Add(this.btnOpenOrgForm);
        this.Name = "MainForm";
        this.Text = "Main Form";
        this.ResumeLayout(false);
        this.PerformLayout();
    }
}
