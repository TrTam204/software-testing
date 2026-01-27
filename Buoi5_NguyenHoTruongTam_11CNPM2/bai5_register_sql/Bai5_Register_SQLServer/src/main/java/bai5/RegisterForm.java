package bai5;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class RegisterForm extends JFrame {

    // Fields
    private JTextField txtMaKH, txtHoTen, txtEmail, txtSDT, txtNgaySinh;
    private JTextArea txtDiaChi;
    private JPasswordField txtMatKhau, txtXacNhanMK;
    private JRadioButton rbNam, rbNu, rbKhac;
    private JCheckBox chkAgree;

    // DAO
    @SuppressWarnings("unused")
    private final CustomerDAO dao = new CustomerDAO();

    public RegisterForm() {
        // Look & feel nhẹ nhàng hơn (giống form hiện đại)
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {}

        setTitle("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 640);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(root);

        // Card viền xanh nhạt như ảnh
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(140, 180, 230), 2, true));
        root.add(card, BorderLayout.CENTER);

        // Title
        JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(18, 10, 10, 10));
        card.add(title, BorderLayout.NORTH);

        // Form panel
        JPanel formWrap = new JPanel(new GridBagLayout());
        formWrap.setBackground(Color.WHITE);
        formWrap.setBorder(new EmptyBorder(10, 65, 10, 65));
        card.add(formWrap, BorderLayout.CENTER);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;

        // Create inputs (bo góc)
        txtMaKH = new RoundedTextField(28);
        txtHoTen = new RoundedTextField(28);
        txtEmail = new RoundedTextField(28);
        txtSDT = new RoundedTextField(28);
        txtNgaySinh = new RoundedTextField(28);

        txtMatKhau = new RoundedPasswordField(28, "Ít nhất 8 ký tự");
        txtXacNhanMK = new RoundedPasswordField(28, "Nhập lại mật khẩu");

        txtDiaChi = new RoundedTextArea(4, 28);
        JScrollPane spDiaChi = new JScrollPane(txtDiaChi);
        spDiaChi.setBorder(new RoundedLineBorder());
        spDiaChi.getViewport().setBackground(Color.WHITE);

        // Placeholder giống ảnh
        applyPlaceholder(txtMaKH, "6-10 ký tự, chỉ chữ và số");
        applyPlaceholder(txtHoTen, "Nhập họ tên đầy đủ");
        applyPlaceholder(txtEmail, "ví dụ: nguyenvana@email.com");
        applyPlaceholder(txtSDT, "Bắt đầu bằng số 0, 10-12 số");
        applyPlaceholder(txtDiaChi, "Nhập địa chỉ chi tiết");
        applyPlaceholder(txtNgaySinh, "mm/dd/yyyy");

        // Filter nhập liệu cơ bản (để đúng yêu cầu)
        ((AbstractDocument) txtMaKH.getDocument()).setDocumentFilter(new AlphaNumFilter(10));
        ((AbstractDocument) txtSDT.getDocument()).setDocumentFilter(new DigitFilter(12));
        ((AbstractDocument) txtNgaySinh.getDocument()).setDocumentFilter(new DateCharFilter(10)); // chỉ cho số và /

        // Row builder
        int row = 0;
        addRow(formWrap, g, row++, labelRequired("Mã Khách Hàng"), txtMaKH);
        addRow(formWrap, g, row++, labelRequired("Họ và Tên"), txtHoTen);
        addRow(formWrap, g, row++, labelRequired("Email"), txtEmail);
        addRow(formWrap, g, row++, labelRequired("Số điện thoại"), txtSDT);
        addRow(formWrap, g, row++, labelRequired("Địa chỉ"), spDiaChi);
        addRow(formWrap, g, row++, labelRequired("Mật khẩu"), txtMatKhau);
        addRow(formWrap, g, row++, labelRequired("Xác nhận Mật khẩu"), txtXacNhanMK);
        addRow(formWrap, g, row++, labelOptional("Ngày sinh"), txtNgaySinh);

        // Gender row giống ảnh
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 0));
        genderPanel.setBackground(Color.WHITE);

        rbNam = new JRadioButton("Nam");
        rbNu = new JRadioButton("Nữ");
        rbKhac = new JRadioButton("Khác");
        styleRadio(rbNam); styleRadio(rbNu); styleRadio(rbKhac);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbNam); bg.add(rbNu); bg.add(rbKhac);

        genderPanel.add(rbNam);
        genderPanel.add(rbNu);
        genderPanel.add(rbKhac);

        addRow(formWrap, g, row++, labelOptional("Giới tính"), genderPanel);

        chkAgree = new JCheckBox("Tôi đồng ý với các điều khoản dịch vụ");
        chkAgree.setBackground(Color.WHITE);
        chkAgree.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        addRow(formWrap, g, row++, labelRequired(""), chkAgree);

        // Buttons giống ảnh
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 10));
        btnPanel.setBackground(Color.WHITE);

        JButton btnDangKy = new JButton("Đăng ký");
        JButton btnNhapLai = new JButton("Nhập lại");
        stylePrimaryButton(btnDangKy);
        styleSecondaryButton(btnNhapLai);

        btnPanel.add(btnDangKy);
        btnPanel.add(btnNhapLai);

        card.add(btnPanel, BorderLayout.SOUTH);

        // Actions
        btnNhapLai.addActionListener(e -> resetForm());
        btnDangKy.addActionListener(e -> handleRegister());

        setVisible(true);
    }

    // ====== ACTIONS ======
    private void resetForm() {
        setTextReset(txtMaKH, "6-10 ký tự, chỉ chữ và số");
        setTextReset(txtHoTen, "Nhập họ tên đầy đủ");
        setTextReset(txtEmail, "ví dụ: nguyenvana@email.com");
        setTextReset(txtSDT, "Bắt đầu bằng số 0, 10-12 số");
        setTextReset(txtDiaChi, "Nhập địa chỉ chi tiết");
        setTextReset(txtNgaySinh, "mm/dd/yyyy");

        ((RoundedPasswordField) txtMatKhau).clear();
        ((RoundedPasswordField) txtXacNhanMK).clear();

        rbNam.setSelected(false);
        rbNu.setSelected(false);
        rbKhac.setSelected(false);
        chkAgree.setSelected(false);
    }

    private void handleRegister() {
        try {
            // Lấy dữ liệu (bỏ placeholder)
            String maKH = getRealText(txtMaKH, "6-10 ký tự, chỉ chữ và số");
            String hoTen = getRealText(txtHoTen, "Nhập họ tên đầy đủ");
            String email = getRealText(txtEmail, "ví dụ: nguyenvana@email.com");
            String sdt = getRealText(txtSDT, "Bắt đầu bằng số 0, 10-12 số");
            String diaChi = getRealText(txtDiaChi, "Nhập địa chỉ chi tiết");
            String ngaySinh = getRealText(txtNgaySinh, "mm/dd/yyyy");

            String mk = new String(txtMatKhau.getPassword()).trim();
            String mk2 = new String(txtXacNhanMK.getPassword()).trim();

            // Validate theo đề
            validateAll(maKH, hoTen, email, sdt, diaChi, mk, mk2, ngaySinh);

            String gioiTinh = rbNam.isSelected() ? "Nam" : rbNu.isSelected() ? "Nữ" : rbKhac.isSelected() ? "Khác" : null;

            Customer c = new Customer(maKH, hoTen, email, sdt, diaChi, mk, ngaySinh, gioiTinh);

            // Insert DB (DAO bạn đã có)
            CustomerDAO.insert(c);

            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công!", "OK", JOptionPane.INFORMATION_MESSAGE);
            resetForm();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateAll(String maKH, String hoTen, String email, String sdt, String diaChi,
                             String mk, String mk2, String ngaySinh) throws Exception {

        // 1) Mã KH: required, 6-10, alnum, unique
        if (isBlank(maKH)) throw new Exception("Mã Khách Hàng là bắt buộc.");
        if (maKH.length() < 6 || maKH.length() > 10) throw new Exception("Mã Khách Hàng phải từ 6 đến 10 ký tự.");
        if (!maKH.matches("^[a-zA-Z0-9]+$")) throw new Exception("Mã Khách Hàng chỉ được chứa chữ và số.");
        if (CustomerDAO.existsById(maKH)) throw new Exception("Mã Khách Hàng đã tồn tại (không được trùng).");

        // 2) Họ tên: required, 5-50, cho phép tiếng Việt + khoảng trắng
        if (isBlank(hoTen)) throw new Exception("Họ và Tên là bắt buộc.");
        if (hoTen.length() < 5 || hoTen.length() > 50) throw new Exception("Họ và Tên phải từ 5 đến 50 ký tự.");
        if (!hoTen.matches("^[\\p{L} .'-]+$")) throw new Exception("Họ và Tên chỉ được chứa chữ và khoảng trắng.");

        // 3) Email: required, format, unique
        if (isBlank(email)) throw new Exception("Email là bắt buộc.");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) throw new Exception("Email không đúng định dạng.");
        if (CustomerDAO.existsByEmail(email)) throw new Exception("Email đã tồn tại (không được trùng).");

        // 4) SĐT: required, digits, 10-12, start 0
        if (isBlank(sdt)) throw new Exception("Số điện thoại là bắt buộc.");
        if (!sdt.matches("^0\\d{9,11}$")) throw new Exception("Số điện thoại phải bắt đầu bằng 0 và dài 10-12 số.");

        // 5) Địa chỉ: required, max 255
        if (isBlank(diaChi)) throw new Exception("Địa chỉ là bắt buộc.");
        if (diaChi.length() > 255) throw new Exception("Địa chỉ tối đa 255 ký tự.");

        // 6) Mật khẩu: required, >= 8
        if (isBlank(mk)) throw new Exception("Mật khẩu là bắt buộc.");
        if (mk.length() < 8) throw new Exception("Mật khẩu phải tối thiểu 8 ký tự.");

        // 7) Xác nhận: required, match
        if (isBlank(mk2)) throw new Exception("Xác nhận mật khẩu là bắt buộc.");
        if (!mk.equals(mk2)) throw new Exception("Xác nhận mật khẩu không khớp.");

        // 8) Ngày sinh: optional; nếu nhập phải đủ 18 tuổi
        if (!isBlank(ngaySinh)) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate dob = LocalDate.parse(ngaySinh, fmt);
                int age = Period.between(dob, LocalDate.now()).getYears();
                if (age < 18) throw new Exception("Nếu nhập ngày sinh, người dùng phải đủ 18 tuổi.");
            } catch (Exception e) {
                throw new Exception("Ngày sinh không hợp lệ. Định dạng đúng: mm/dd/yyyy");
            }
        }

        // 10) Điều khoản: bắt buộc tick
        if (!chkAgree.isSelected()) throw new Exception("Bạn phải đồng ý với các điều khoản dịch vụ.");
    }

    // ====== UI HELPERS ======
    private static JLabel labelRequired(String text) {
        JLabel lb = new JLabel(text.isEmpty() ? "" : (text + " *"));
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lb.setForeground(text.isEmpty() ? new Color(0,0,0,0) : new Color(30, 30, 30));
        return lb;
    }

    private static JLabel labelOptional(String text) {
        JLabel lb = new JLabel(text);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lb.setForeground(new Color(30, 30, 30));
        return lb;
    }

    private static void addRow(JPanel panel, GridBagConstraints g, int row, JComponent left, JComponent right) {
        g.gridy = row;

        g.gridx = 0;
        g.weightx = 0.25;
        panel.add(left, g);

        g.gridx = 1;
        g.weightx = 0.75;
        panel.add(right, g);
    }

    private static void styleRadio(JRadioButton rb) {
        rb.setBackground(Color.WHITE);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setFocusable(false);
    }

    private static void stylePrimaryButton(JButton b) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setPreferredSize(new Dimension(120, 36));
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(0, 122, 255));
        b.setBorder(new RoundedButtonBorder());
    }

    private static void styleSecondaryButton(JButton b) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setPreferredSize(new Dimension(120, 36));
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(120, 120, 120));
        b.setBorder(new RoundedButtonBorder());
    }

    // Placeholder cho JTextField / JTextArea
    private static void applyPlaceholder(JTextComponent comp, String placeholder) {
        comp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comp.setForeground(new Color(130, 130, 130));
        comp.setText(placeholder);

        comp.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (comp.getText().equals(placeholder)) {
                    comp.setText("");
                    comp.setForeground(new Color(30, 30, 30));
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (comp.getText().trim().isEmpty()) {
                    comp.setForeground(new Color(130, 130, 130));
                    comp.setText(placeholder);
                }
            }
        });
    }

    private static void setTextReset(JTextComponent comp, String placeholder) {
        comp.setForeground(new Color(130, 130, 130));
        comp.setText(placeholder);
    }

    private static String getRealText(JTextComponent comp, String placeholder) {
        String t = comp.getText().trim();
        if (t.equals(placeholder)) return "";
        return t;
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

    // ====== CUSTOM COMPONENTS ======

    // Rounded TextField
    static class RoundedTextField extends JTextField {
        RoundedTextField(int cols) {
            super(cols);
            setOpaque(false);
            setBorder(new RoundedLineBorder());
            setBackground(Color.WHITE);
            setForeground(new Color(30, 30, 30));
            setPreferredSize(new Dimension(10, 34));
            setMargin(new Insets(8, 12, 8, 12));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Rounded TextArea (vẫn dùng JScrollPane)
    static class RoundedTextArea extends JTextArea {
        RoundedTextArea(int rows, int cols) {
            super(rows, cols);
            setLineWrap(true);
            setWrapStyleWord(true);
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setFont(new Font("Segoe UI", Font.PLAIN, 13));
        }
    }

    // Password có placeholder
    static class RoundedPasswordField extends JPasswordField {
        private final String placeholder;
        RoundedPasswordField(int cols, String placeholder) {
            super(cols);
            this.placeholder = placeholder;
            setOpaque(false);
            setBorder(new RoundedLineBorder());
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(10, 34));
            setMargin(new Insets(8, 12, 8, 12));
            setEchoChar('•');

            // Khi empty thì vẽ placeholder (không nhét text vào password)
            addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) { repaint(); }
                @Override public void focusLost(FocusEvent e) { repaint(); }
            });
        }
        void clear() { setText(""); repaint(); }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
            g2.dispose();

            super.paintComponent(g);

            if (getPassword().length == 0 && !hasFocus()) {
                Graphics2D g3 = (Graphics2D) g.create();
                g3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                g3.setColor(new Color(130, 130, 130));
                g3.drawString(placeholder, 12, getHeight()/2 + 5);
                g3.dispose();
            }
        }
    }

    static class RoundedLineBorder extends EmptyBorder {
        RoundedLineBorder() { super(8, 12, 8, 12); }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(210, 210, 210));
            g2.drawRoundRect(x, y, width-1, height-1, 12, 12);
            g2.dispose();
        }
    }

    static class RoundedButtonBorder extends EmptyBorder {
        RoundedButtonBorder() { super(6, 18, 6, 18); }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0,0,0,0));
            g2.drawRoundRect(x, y, width-1, height-1, 14, 14);
            g2.dispose();
        }
    }

    // ====== DOCUMENT FILTERS ======
    // Alnum + maxLen
    static class AlphaNumFilter extends DocumentFilter {
        private final int maxLen;
        AlphaNumFilter(int maxLen) { this.maxLen = maxLen; }

        @Override public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) return;
            String cur = fb.getDocument().getText(0, fb.getDocument().getLength());
            String next = new StringBuilder(cur).replace(offset, offset + length, text).toString();
            if (next.length() > maxLen) return;
            if (!next.matches("^[a-zA-Z0-9]*$")) return;
            super.replace(fb, offset, length, text, attrs);
        }
    }

    // Digits only + maxLen
    static class DigitFilter extends DocumentFilter {
        private final int maxLen;
        DigitFilter(int maxLen) { this.maxLen = maxLen; }

        @Override public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) return;
            String cur = fb.getDocument().getText(0, fb.getDocument().getLength());
            String next = new StringBuilder(cur).replace(offset, offset + length, text).toString();
            if (next.length() > maxLen) return;
            if (!next.matches("^\\d*$")) return;
            super.replace(fb, offset, length, text, attrs);
        }
    }

    // Date: chỉ số và /
    static class DateCharFilter extends DocumentFilter {
        private final int maxLen;
        DateCharFilter(int maxLen) { this.maxLen = maxLen; }

        @Override public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) return;
            String cur = fb.getDocument().getText(0, fb.getDocument().getLength());
            String next = new StringBuilder(cur).replace(offset, offset + length, text).toString();
            if (next.length() > maxLen) return;
            if (!next.matches("^[0-9/]*$")) return;
            super.replace(fb, offset, length, text, attrs);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterForm::new);
    }
}
