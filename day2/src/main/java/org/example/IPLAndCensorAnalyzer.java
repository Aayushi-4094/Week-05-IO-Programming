import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class IPLAndCensorAnalyzer {

    // Model class
    static class IPLMatch {
        public int match_id;
        public String team1;
        public String team2;
        public Map<String, Integer> score;
        public String winner;
        public String player_of_match;

        public IPLMatch(int match_id, String team1, String team2, Map<String, Integer> score,
                        String winner, String player_of_match) {
            this.match_id = match_id;
            this.team1 = team1;
            this.team2 = team2;
            this.score = score;
            this.winner = winner;
            this.player_of_match = player_of_match;
        }

        public JSONObject toJSONObject() {
            JSONObject obj = new JSONObject();
            obj.put("match_id", match_id);
            obj.put("team1", team1);
            obj.put("team2", team2);
            obj.put("score", score);
            obj.put("winner", winner);
            obj.put("player_of_match", player_of_match);
            return obj;
        }
    }

    // Censorship utilities
    static class CensorUtils {
        public static String maskTeam(String teamName) {
            int lastSpace = teamName.lastIndexOf(" ");
            if (lastSpace != -1) {
                return teamName.substring(0, lastSpace + 1) + "***";
            }
            return "***";
        }

        public static String redactPlayer(String playerName) {
            return "REDACTED";
        }
    }

    // ✅ MAIN METHOD: JSON Processor
    public static void processJson(String inputPath, String outputPath) throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get(inputPath)));
        JSONArray inputArray = new JSONArray(jsonContent);
        JSONArray outputArray = new JSONArray();

        for (int i = 0; i < inputArray.length(); i++) {
            JSONObject match = inputArray.getJSONObject(i);

            // Mask team names
            String team1 = CensorUtils.maskTeam(match.getString("team1"));
            String team2 = CensorUtils.maskTeam(match.getString("team2"));
            String winner = CensorUtils.maskTeam(match.getString("winner"));
            String redactedPlayer = CensorUtils.redactPlayer(match.getString("player_of_match"));

            JSONObject scoreObj = match.getJSONObject("score");
            Map<String, Integer> newScore = new LinkedHashMap<>();
            newScore.put(team1, scoreObj.getInt(match.getString("team1")));
            newScore.put(team2, scoreObj.getInt(match.getString("team2")));

            IPLMatch censoredMatch = new IPLMatch(
                    match.getInt("match_id"),
                    team1, team2, newScore, winner, redactedPlayer
            );
            outputArray.put(censoredMatch.toJSONObject());
        }

        Files.write(Paths.get(outputPath), outputArray.toString(2).getBytes());
        System.out.println("✅ Censored JSON written to: " + outputPath);
    }

    // ✅ MAIN METHOD: CSV Processor
    public static void processCsv(String inputPath, String outputPath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

        String header = reader.readLine();
        writer.write(header + "\n");

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            parts[1] = CensorUtils.maskTeam(parts[1]); // team1
            parts[2] = CensorUtils.maskTeam(parts[2]); // team2
            parts[5] = CensorUtils.maskTeam(parts[5]); // winner
            parts[6] = CensorUtils.redactPlayer(parts[6]); // player_of_match

            writer.write(String.join(",", parts) + "\n");
        }

        reader.close();
        writer.close();
        System.out.println("✅ Censored CSV written to: " + outputPath);
    }

    // ✅ MAIN ENTRY POINT
    public static void main(String[] args) throws Exception {
        // Uncomment one of the following based on input type:

        // ➤ JSON Mode
        processJson("ipl_data.json", "ipl_data_censored.json");

        // ➤ CSV Mode
        // processCsv("ipl_data.csv", "ipl_data_censored.csv");
    }
}
