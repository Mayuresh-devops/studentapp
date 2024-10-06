CREATE TABLE IF NOT EXISTS students (
    student_id INT NOT NULL AUTO_INCREMENT,
    student_name VARCHAR(100) NOT NULL,
    student_address VARCHAR(100) NOT NULL,   -- More descriptive
    student_age INT NOT NULL,                -- Use INT for age
    student_qualification VARCHAR(50) NOT NULL,  -- More descriptive and slightly extended
    student_percentage DECIMAL(5,2) NOT NULL, -- Use DECIMAL for percentage (e.g., 85.50%)
    student_year_passed YEAR NOT NULL,       -- Use YEAR for storing the year
    PRIMARY KEY (student_id)
);
