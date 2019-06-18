CREATE TABLE shipattributes
(
    id INT NOT NULL,
    name TEXT NOT NULL,
    armor INT NOT NULL,
    speed INT NOT NULL,
    visibility INT NOT NULL,
    damage INT NOT NULL,
    price INT NOT NULL,
    maxnumber INT NOT NULL,
    PRIMARY KEY (id, name)
)

CREATE TABLE variousdata
(
    key INT PRIMARY KEY NOT NULL,
    planetresources INT NOT NULL,
    makevisiblerange INT NOT NULL,
    planetnumber INT NOT NULL
)

