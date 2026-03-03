const express = require('express');
const sql = require('mssql/msnodesqlv8');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

const config = {
    server: '(localdb)\\MSSQLLocalDB',
    database: 'ShopVN',
    driver: 'msnodesqlv8',
    options: {
        trustedConnection: true
    }
};

app.post('/register', async (req, res) => {
    try {
        await sql.connect(config);

        const { fullname, username, email, phone, password } = req.body;

        const request = new sql.Request();

        await request
            .input('FullName', sql.NVarChar, fullname)
            .input('Username', sql.VarChar, username)
            .input('Email', sql.VarChar, email)
            .input('Phone', sql.VarChar, phone)
            .input('PasswordHash', sql.VarChar, password)
            .query(`
                INSERT INTO Users (FullName, Username, Email, Phone, PasswordHash)
                VALUES (@FullName, @Username, @Email, @Phone, @PasswordHash)
            `);

        res.json({ success: true });

    } catch (err) {
        console.error(err);
        res.status(500).json({ success: false, message: err.message });
    }
});

app.listen(3000, () => {
    console.log("Server đang chạy tại http://localhost:3000");
});