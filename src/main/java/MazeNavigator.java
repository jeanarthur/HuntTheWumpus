import java.util.ArrayList;
import java.util.List;

public class MazeNavigator {

    public List<Cave> getCaveList(Cave root){
        List<Cave> list = new ArrayList<>();
        getCaves(root, null, list);
        return list;
    }

    private void getCaves(Cave cave, Coordinates coordinate, List<Cave> list){
        if (cave == null || list.contains(cave)){
            return;
        }

        list.add(cave);

        Coordinates origin = Coordinates.getOpposite(coordinate);
        for (Coordinates direction : Coordinates.values()){
            if (!direction.equals(origin)){
                getCaves(cave.get(direction), direction, list);
            }
        }
    }

}
