public class Main {

    public static void main(String[] args) {
//        GameController gameController = new GameController();
//
//        gameController.start();

        Cave root = new Cave();
        VisualMap visualMap = new VisualMap(root);
        visualMap.print();

        Cave east = new Cave(null, null, null, root);
        root.setEast(east);

        visualMap.createVisualConnection(root, east);
        visualMap.print();

        Cave north = new Cave(null, root, null, null);
        root.setNorth(north);

        visualMap.createVisualConnection(root, north);
        visualMap.print();

        Cave west = new Cave(null, null, root, null);
        root.setWest(west);

        visualMap.createVisualConnection(root, west);
        visualMap.print();

        Cave northWest = new Cave(null, west, north, null);
        north.setWest(northWest);
        west.setNorth(northWest);

        visualMap.createVisualConnection(north, northWest);
        visualMap.createVisualConnection(west, northWest);
        visualMap.print();

    }

}
