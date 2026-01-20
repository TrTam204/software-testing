namespace QuanLyToChuc.WinForms.Forms;

partial class DirectorForm
{
    private System.ComponentModel.IContainer components = null;
    private System.Windows.Forms.Label lblInfo;

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
        this.lblInfo = new System.Windows.Forms.Label();
        this.SuspendLayout();
        // 
        // lblInfo
        // 
        this.lblInfo.AutoSize = true;
        this.lblInfo.Location = new System.Drawing.Point(50, 50);
        this.lblInfo.Name = "lblInfo";
        this.lblInfo.Size = new System.Drawing.Size(100, 23);
        this.lblInfo.TabIndex = 0;
        this.lblInfo.Text = "Info";
        // 
        // DirectorForm
        // 
        this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(400, 200);
        this.Controls.Add(this.lblInfo);
        this.Name = "DirectorForm";
        this.Text = "Director Form";
        this.ResumeLayout(false);
        this.PerformLayout();
    }
}
