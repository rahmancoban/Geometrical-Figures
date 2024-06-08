package com.example.geometrical_figures;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FigureActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textDescription, textFormulas, textResult;
    EditText input1, input2;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);

        ActionBar actionBar = getSupportActionBar();
        imageView = findViewById(R.id.image_view);
        textDescription = findViewById(R.id.text_description);
        textFormulas = findViewById(R.id.text_formulas);
        textResult = findViewById(R.id.text_result);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        buttonCalculate = findViewById(R.id.button_calculate);

        Intent intent = getIntent();
        String figureName = intent.getStringExtra("figure_name");

        if (actionBar != null) {
            actionBar.setTitle(figureName);
        }

        setFigureDetails(figureName);

        buttonCalculate.setOnClickListener(v -> calculateResults(figureName));
    }

    private void setFigureDetails(String figureName) {
        int imageId = getResources().getIdentifier("figure_" + figureName.toLowerCase(), "drawable", getPackageName());
        imageView.setImageResource(imageId);

        String[] details = getFigureDetails(figureName);
        textDescription.setText(details[0]);
        textFormulas.setText(details[1]);

        configureInputs(figureName);
    }

    private void configureInputs(String figureName) {
        switch (figureName) {
            case "Circle":
            case "Square":
            case "Triangle":
            case "Pentagon":
            case "Hexagon":
            case "Sphere":
            case "Cube":
                input1.setVisibility(View.VISIBLE);
                input1.setHint("Enter side length");
                break;
            case "Rectangle":
            case "Ellipse":
            case "Cylinder":
            case "Cone":
            case "Pyramid":
                input1.setVisibility(View.VISIBLE);
                input2.setVisibility(View.VISIBLE);
                input1.setHint("Enter first dimension");
                input2.setHint("Enter second dimension");
                break;
        }
        buttonCalculate.setVisibility(View.VISIBLE);
    }

    private void calculateResults(String figureName) {
        double value1 = 0, value2 = 0;
        if (input1.getVisibility() == View.VISIBLE && !input1.getText().toString().isEmpty()) {
            value1 = Double.parseDouble(input1.getText().toString());
        }
        if (input2.getVisibility() == View.VISIBLE && !input2.getText().toString().isEmpty()) {
            value2 = Double.parseDouble(input2.getText().toString());
        }

        String result = getResultString(figureName, value1, value2);
        textResult.setText(result);
        textResult.setVisibility(View.VISIBLE);
    }

    private String getResultString(String figureName, double value1, double value2) {
        switch (figureName) {
            case "Circle":
                double circleArea = Math.PI * value1 * value1;
                double circleCircumference = 2 * Math.PI * value1;
                return "Result:\nArea: " + circleArea + "\nCircumference: " + circleCircumference;
            case "Square":
                double squareArea = value1 * value1;
                double squarePerimeter = 4 * value1;
                return "Result:\nArea: " + squareArea + "\nPerimeter: " + squarePerimeter;
            case "Triangle":
                double triangleArea = (Math.sqrt(3) / 4) * value1 * value1;
                double trianglePerimeter = 3 * value1;
                return "Result:\nArea: " + triangleArea + "\nPerimeter: " + trianglePerimeter;
            case "Rectangle":
                double rectangleArea = value1 * value2;
                double rectanglePerimeter = 2 * (value1 + value2);
                return "Result:\nArea: " + rectangleArea + "\nPerimeter: " + rectanglePerimeter;
            case "Ellipse":
                double ellipseArea = Math.PI * value1 * value2;
                return "Result:\nArea: " + ellipseArea;
            case "Pentagon":
                double pentagonArea = (Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * value1 * value1) / 4;
                double pentagonPerimeter = 5 * value1;
                return "Result:\nArea: " + pentagonArea + "\nPerimeter: " + pentagonPerimeter;
            case "Hexagon":
                double hexagonArea = (3 * Math.sqrt(3) * value1 * value1) / 2;
                double hexagonPerimeter = 6 * value1;
                return "Result:\nArea: " + hexagonArea + "\nPerimeter: " + hexagonPerimeter;
            case "Sphere":
                double sphereSurfaceArea = 4 * Math.PI * value1 * value1;
                double sphereVolume = (4 / 3) * Math.PI * value1 * value1 * value1;
                return "Result:\nSurface Area: " + sphereSurfaceArea + "\nVolume: " + sphereVolume;
            case "Cube":
                double cubeSurfaceArea = 6 * value1 * value1;
                double cubeVolume = value1 * value1 * value1;
                return "Result:\nSurface Area: " + cubeSurfaceArea + "\nVolume: " + cubeVolume;
            case "Cylinder":
                double cylinderSurfaceArea = 2 * Math.PI * value1 * (value1 + value2);
                double cylinderVolume = Math.PI * value1 * value1 * value2;
                return "Result:\nSurface Area: " + cylinderSurfaceArea + "\nVolume: " + cylinderVolume;
            case "Cone":
                double coneSurfaceArea = Math.PI * value1 * (value1 + Math.sqrt(value2 * value2 + value1 * value1));
                double coneVolume = (1 / 3) * Math.PI * value1 * value1 * value2;
                return "Result:\nSurface Area: " + coneSurfaceArea + "\nVolume: " + coneVolume;
            case "Pyramid":
                double pyramidSurfaceArea = value1 * value1 + 2 * value1 * Math.sqrt((value1 / 2) * (value1 / 2) + value2 * value2);
                double pyramidVolume = (1 / 3) * value1 * value1 * value2;
                return "Result:\nSurface Area: " + pyramidSurfaceArea + "\nVolume: " + pyramidVolume;
            default:
                return "Invalid figure";
        }
    }

    private String[] getFigureDetails(String figureName) {
        switch (figureName) {
            case "Circle":
                return new String[]{"A round shape whose boundary (the circumference) consists of points equidistant from the center.",
                        "Area = π × radius²\nCircumference = 2π × radius"};
            case "Triangle":
                return new String[]{"A polygon with three edges and three vertices.",
                        "Area = 0.5 × base × height\nPerimeter = side1 + side2 + side3"};
            case "Square":
                return new String[]{"A regular quadrilateral with all four sides and angles equal.",
                        "Area = side²\nPerimeter = 4 × side"};
            case "Rectangle":
                return new String[]{"A quadrilateral with opposite sides parallel and equal, and four right angles.",
                        "Area = length × width\nPerimeter = 2 × (length + width)"};
            case "Ellipse":
                return new String[]{"A curve on a plane surrounding two focal points.",
                        "Area = π × semi-major axis × semi-minor axis\nCircumference = Approximated by Ramanujan's formula"};
            case "Pentagon":
                return new String[]{"A five-sided polygon.",
                        "Area = (1/4) × √(5(5 + 2√5)) × side²\nPerimeter = 5 × side"};
            case "Hexagon":
                return new String[]{"A six-sided polygon.",
                        "Area = (3√3/2) × side²\nPerimeter = 6 × side"};
            case "Sphere":
                return new String[]{"A perfectly round geometrical object in three-dimensional space.",
                        "Surface Area = 4π × radius²\nVolume = (4/3)π × radius³"};
            case "Cube":
                return new String[]{"A three-dimensional solid object bounded by six square faces.",
                        "Surface Area = 6 × side²\nVolume = side³"};
            case "Cylinder":
                return new String[]{"A three-dimensional geometric figure with parallel sides and a circular or oval section.",
                        "Surface Area = 2π × radius × (height + radius)\nVolume = π × radius² × height"};
            case "Cone":
                return new String[]{"A solid that has a circular base and a single vertex.",
                        "Surface Area = π × radius × (radius + √(height² + radius²))\nVolume = (1/3)π × radius² × height"};
            case "Pyramid":
                return new String[]{"A polyhedron formed by connecting a polygonal base and a point.",
                        "Surface Area = Base area + 0.5 × perimeter × slant height\nVolume = (1/3) × base area × height"};
            default:
                return new String[]{"Description not available", "No formulas available"};
        }
    }
}
