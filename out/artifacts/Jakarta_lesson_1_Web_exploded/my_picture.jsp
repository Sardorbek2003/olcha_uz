<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image Example</title>
    <style>
        /* Body va html teglarini butun ekranni egallash uchun o'zgartiramiz */
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
            background-color: brown;
            border-bottom-color: #007BFF;
        }

        img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
</head>
<body>
<p>Salom</p>
<img src="web/pdp_logo.jpg" alt="Image">

</body>
</html>
