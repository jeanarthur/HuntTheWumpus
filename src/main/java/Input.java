import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public int getInteger() throws NumberFormatException {
        return Integer.parseInt(scanner.nextLine());
    }

    public String get() {
        return scanner.nextLine();
    }

    public Map<String, ValidInput> getValidInputMap(Cave cave){
        Map<String, ValidInput> validInputMap = new HashMap<>();

        if (cave.getNorth() != null){
            validInputMap.put("w", new ValidInput("w", "Mover para o norte", Coordinates.NORTH));
        }

        if (cave.getWest() != null){
            validInputMap.put("a", new ValidInput("a", "Mover para o oeste", Coordinates.WEST));
        }

        if (cave.getSouth() != null){
            validInputMap.put("s", new ValidInput("s", "Mover para o sul", Coordinates.SOUTH));
        }

        if (cave.getEast() != null){
            validInputMap.put("d", new ValidInput("d", "Mover para o leste", Coordinates.EAST));
        }

        // validInputMap.put("e", new ValidInput("e", "Atirar flecha"));
        return validInputMap;
    }

}
