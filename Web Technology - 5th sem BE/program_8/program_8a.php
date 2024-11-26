<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Visitor Counter</title>
        <style>
            body {
                font-family: Arial, sans-serif; 
                line-height: 1.6;
                margin: 0; 
                padding: 20px;
                background-color: #f4f4f4;
            }

            .container {
                max-width: 600px; 
                margin: auto; 
                background: white; 
                padding: 20px; 
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }

            h1 {
                color: #333;
                text-align: center;
            }

            .counter {
                font-size: 24px; 
                text-align: center; 
                margin-top: 20px;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h1>Welcome to Our Website</h1>
            <div class="counter">
                <?php
                    $counterFile = 'visitor_count.txt';

                    // Read the current count
                    if (file_exists($counterFile)) {
                        $count = (int)file_get_contents($counterFile);
                    } else {
                        $count = 0;
                    }

                    // Increment the count
                    $count++;
                    
                    // Save the new count file_put_contents($counterFile, $count);
                    // Display the count
                    echo "<h2>Visitor Count</h2>";

                    echo "<p>You are visitor number: $count</p>";
                ?>
            </div>
        </div>
    </body>

</html>