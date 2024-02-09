const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public')); // Make sure to create a 'public' folder and place your HTML file there

app.post('/submit-form', (req, res) => {
    const formData = req.body;
    // Process the form data as per your requirement
    console.log(formData);
    res.send('Form submitted successfully.');
});

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
