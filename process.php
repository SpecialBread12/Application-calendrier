 
<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *"); // Permettre les requêtes depuis n'importe quelle origine
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

$servername = "54.39.97.222"; // Remplace par ton serveur MySQL
$username = "root";        // Ton nom d'utilisateur MySQL
$password = "";            // Ton mot de passe MySQL
$dbname = "calendrier";   // Le nom de ta base de données

$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die(json_encode(["success" => false, "message" => "Échec de connexion à la base de données"]));
}

// Vérifier si la requête est bien en POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Récupérer les données envoyées en JSON
    $input = json_decode(file_get_contents("php://input"), true);

    // Vérifier si les champs nécessaires sont présents
    if (isset($input["nom"]) && isset($input["date"])) {
        $name = $conn->real_escape_string($input["nom"]);
        $date = $conn->real_escape_string($input["date"]);

        // Insérer les données dans la base de données
        $sql = "INSERT INTO users (name, email) VALUES ('$name', '$date')";

        if ($conn->query($sql) === TRUE) {
            echo json_encode(["success" => true, "message" => "Données enregistrées avec succès"]);
        } else {
            echo json_encode(["success" => false, "message" => "Erreur lors de l'insertion"]);
        }
    } else {
        echo json_encode(["success" => false, "message" => "Données incomplètes"]);
    }
} else {
    echo json_encode(["success" => false, "message" => "Méthode non autorisée"]);
}

$conn->close();
?>
