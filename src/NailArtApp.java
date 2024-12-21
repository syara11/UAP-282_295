import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Aplikasi NailArtApp untuk mengelola reservasi layanan nail art.
 * Aplikasi ini memungkinkan pengguna untuk memasukkan nama, tanggal, dan waktu reservasi
 * serta menampilkan daftar reservasi dalam bentuk tabel.
 */
public class NailArtApp {
    // Komponen UI utama
    private JFrame frame;  // Frame utama aplikasi
    private JTextField nameField;  // Field untuk input nama pelanggan
    private JTextField dateField;  // Field untuk input tanggal reservasi
    private JTextField timeField;  // Field untuk input waktu reservasi
    private JTable reservationTable;  // Tabel untuk menampilkan daftar reservasi
    private DefaultTableModel tableModel;  // Model tabel untuk mengelola data tabel
    private ArrayList<Reservation> reservations;  // Penyimpanan data reservasi secara internal

    /**
     * Konstruktor untuk memulai aplikasi.
     * Memanggil metode untuk menginisialisasi komponen UI dan logika aplikasi.
     */
    public NailArtApp() {
        initializeComponents();  // Memanggil metode untuk menginisialisasi komponen
    }

    /**
     * Menginisialisasi semua komponen UI aplikasi.
     * Ini mencakup pembuatan frame, label, field input, tombol, dan tabel reservasi.
     */
    private void initializeComponents() {
        reservations = new ArrayList<>();  // Menginisialisasi array list untuk menyimpan data reservasi

        // Membuat frame utama
        frame = new JFrame("Reservasi NailArt");
        frame.setSize(400, 300);  // Mengatur ukuran frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Menutup aplikasi saat frame ditutup
        frame.setLayout(null);  // Menggunakan layout absolut (koordinat manual)

        // Mengubah warna latar belakang frame
        frame.getContentPane().setBackground(Color.MAGENTA);

        // Menambahkan label dan input field
        addLabelsAndFields();
        // Menambahkan tombol "Reservasi"
        addSubmitButton();
        // Menambahkan tabel untuk menampilkan data reservasi
        addReservationTable();

        // Menampilkan frame
        frame.setVisible(true);
    }

    /**
     * Menambahkan label dan input field untuk nama, tanggal, dan waktu reservasi.
     * Label dan field input ditempatkan pada posisi yang telah ditentukan di dalam frame.
     */
    private void addLabelsAndFields() {
        // Menambahkan label dan input untuk nama pelanggan
        JLabel nameLabel = createLabel("Nama:", 10, 10, 80, 25);
        nameField = createTextField(100, 10, 160, 25, Color.WHITE, Color.BLACK); // Mengubah warna teks dan latar belakang

        // Menambahkan label dan input untuk tanggal (format YYYY-MM-DD)
        JLabel dateLabel = createLabel("Tanggal (YYYY-MM-DD):", 10, 40, 160, 25);
        dateField = createTextField(180, 40, 80, 25, Color.WHITE, Color.BLACK); // Mengubah warna teks dan latar belakang

        // Menambahkan label dan input untuk waktu (format HH:MM)
        JLabel timeLabel = createLabel("Waktu (HH:MM):", 10, 70, 160, 25);
        timeField = createTextField(180, 70, 80, 25, Color.WHITE, Color.BLACK); // Mengubah warna teks dan latar belakang
    }

    /**
     * Menambahkan tombol "Reservasi" yang digunakan untuk memasukkan data reservasi ke dalam tabel.
     * Mengatur aksi tombol untuk memproses reservasi dan memvalidasi input.
     */
    private void addSubmitButton() {
        // Membuat tombol "Reservasi"
        JButton submitButton = new JButton("Reservasi");
        submitButton.setBounds(10, 100, 250, 25);
        submitButton.setBackground(Color.GREEN); // Mengubah warna latar belakang tombol
        submitButton.setForeground(Color.WHITE); // Mengubah warna teks tombol
        // Mengatur aksi tombol untuk memproses reservasi
        submitButton.addActionListener(this::handleSubmitAction);
        frame.add(submitButton); // Menambahkan tombol ke frame
    }

    /**
     * Menambahkan tabel untuk menampilkan daftar reservasi.
     * Tabel ini menggunakan model DefaultTableModel untuk mengelola data yang ditampilkan.
     */
    private void addReservationTable() {
        // Membuat kolom untuk tabel
        String[] columnNames = {"Nama", "Tanggal", "Waktu"};
        tableModel = new DefaultTableModel(columnNames, 0);  // Membuat model tabel kosong
        reservationTable = new JTable(tableModel);  // Membuat tabel dengan model

        // Mengubah warna latar belakang dan teks pada tabel
        reservationTable.setBackground(Color.WHITE);
        reservationTable.setForeground(Color.BLACK);

        // Menambahkan scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        scrollPane.setBounds(10, 130, 360, 120);
        frame.add(scrollPane);  // Menambahkan scroll pane ke frame
    }

    /**
     * Membuat label dengan teks, posisi, dan ukuran tertentu.
     *
     * @param text  Teks untuk label
     * @param x     Posisi x
     * @param y     Posisi y
     * @param width Lebar label
     * @param height Tinggi label
     * @return Label yang telah dibuat
     */
    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(Color.BLACK); // Mengubah warna teks label
        frame.add(label);  // Menambahkan label ke frame
        return label;
    }

    /**
     * Membuat text field dengan posisi dan ukuran tertentu, dan mengatur warna latar belakang serta teks.
     *
     * @param x      Posisi x
     * @param y      Posisi y
     * @param width  Lebar text field
     * @param height Tinggi text field
     * @param bgColor Warna latar belakang text field
     * @param textColor Warna teks pada text field
     * @return TextField yang telah dibuat
     */
    private JTextField createTextField(int x, int y, int width, int height, Color bgColor, Color textColor) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(bgColor);
        textField.setForeground(textColor);
        frame.add(textField);  // Menambahkan text field ke frame
        return textField;
    }

    /**
     * Menangani aksi ketika tombol "Reservasi" ditekan.
     * Validasi input dilakukan dan data reservasi ditambahkan ke tabel.
     *
     * @param e Aksi tombol yang dipicu
     */
    private void handleSubmitAction(ActionEvent e) {
        // Mengambil data dari input field
        String name = nameField.getText();
        String date = dateField.getText();
        String time = timeField.getText();

        try {
            // Memvalidasi input
            validateInputs(name, date, time);

            // Membuat objek reservasi baru
            Reservation reservation = new Reservation(name, date, time);
            reservations.add(reservation);  // Menambahkan ke daftar internal
            tableModel.addRow(new Object[]{name, date, time});  // Menambahkan data ke tabel

            // Mengosongkan input field
            clearFields();
            // Menampilkan pesan sukses
            JOptionPane.showMessageDialog(frame, "Reservasi berhasil!");
        } catch (IllegalArgumentException ex) {
            // Menampilkan pesan error jika validasi gagal
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Memvalidasi input dari pengguna.
     * Memeriksa apakah nama, tanggal, dan waktu telah diisi dengan benar.
     *
     * @param name  Nama pelanggan
     * @param date  Tanggal reservasi
     * @param time  Waktu reservasi
     * @throws IllegalArgumentException Jika input tidak valid
     */
    private void validateInputs(String name, String date, String time) {
        // Validasi bahwa semua field diisi
        if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
            throw new IllegalArgumentException("Nama, tanggal, dan waktu tidak boleh kosong.");
        }

        // Validasi bahwa nama hanya mengandung huruf
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama hanya boleh berisi huruf alfabet.");
        }

        // Validasi format tanggal (YYYY-MM-DD)
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Tanggal harus dalam format YYYY-MM-DD dan hanya berisi angka.");
        }

        // Validasi format waktu (HH:MM)
        if (!time.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Waktu harus dalam format HH:MM dan hanya berisi angka.");
        }
    }

    /**
     * Mengosongkan semua input field setelah reservasi berhasil dilakukan.
     */
    private void clearFields() {
        nameField.setText("");
        dateField.setText("");
        timeField.setText("");
    }

    /**
     * Titik masuk program.
     * Membuat dan menjalankan instance aplikasi.
     */
    public static void main(String[] args) {
        new NailArtApp();  // Membuat dan menjalankan instance aplikasi
    }

    /**
     * Kelas untuk merepresentasikan data reservasi.
     * Menyimpan informasi nama, tanggal, dan waktu reservasi.
     */
    class Reservation {
        private final String name;  // Nama pelanggan
        private final String date;  // Tanggal reservasi
        private final String time;  // Waktu reservasi

        /**
         * Konstruktor untuk membuat objek Reservasi baru.
         *
         * @param name Nama pelanggan
         * @param date Tanggal reservasi
         * @param time Waktu reservasi
         */
        public Reservation(String name, String date, String time) {
            this.name = name;
            this.date = date;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }
    }
}
