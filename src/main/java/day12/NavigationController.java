package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NavigationController {
    private List<Node> nodes = new ArrayList<>();
    private int pathNumber = 0;

    public long countPathes() {
        List<String> lines = readFile(Path.of("src/main/resources/day12.txt"));
        readFromLines(lines);
        List<Node> route = new ArrayList<>();
        route.add(findNodeByName(nodes, "start"));
        findPath(nodes, "start", "end", new ArrayList<>(route));
        System.out.println(pathNumber);
        return 0;
    }

    public long countPathes2() {
        pathNumber = 0;
        List<String> lines = readFile(Path.of("src/main/resources/day12.txt"));
        readFromLines(lines);
        List<Node> route = new ArrayList<>();
        //route.add(findNodeByName(nodes,"start"));
        findPath2(nodes, "start", "end", new ArrayList<>(route));
        System.out.println(pathNumber);
        return 0;
    }

    private int findPath(List<Node> findInNodes, String start, String end, List<Node> route) {
        if (start.equals(end)) {
            pathNumber++;
            //System.out.println(route);
            return 0;
        }

        Node startNode = findNodeByName(findInNodes, start);
        route.add(startNode);

        for (int i = 0; i < startNode.getNeighborNodes().size(); i++) {
            Node n = startNode.getNeighborNodes().get(i);
            String next = n.getName();
            if (!(next.equals(next.toLowerCase(Locale.ROOT)) && findNodeByName(route, next) != null)) {
                //route.add(findNodeByName(findInNodes, next));
                findPath(findInNodes, next, end, new ArrayList<>(route));
            }
        }
        return 1;
    }

    private boolean countNodeByName(List<Node> findInNodes, String nodeToFind) {
        List<NameCount> names = new ArrayList<>();

        for (Node node : findInNodes) {
            if (nodeToFind.equals("start") && node.getName().equals("start")) {
                return true;
            }
            if (nodeToFind.equals("end") && node.getName().equals("end")) {
                return true;
            }

        }

        for (Node node : findInNodes) {
            if (node.getName().equals(node.getName().toLowerCase(Locale.ROOT))) {
                boolean isIn = false;
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).getName().equals(node.getName())) {
                        names.get(i).incCount();
                        isIn = true;
                    }
                }
                if (!isIn) {
                    names.add(new NameCount(node.getName(), 1));
                }
            }
        }

        int counter = 0;
        for (NameCount nameCount: names) {
            if (nameCount.getCount() > 2) {
                return true;
            }

            if (nameCount.getCount() == 2) {
                counter++;
            }
        }
        if (counter > 1){
            return true;
        }

        return false;
    }

    private int findPath2(List<Node> findInNodes, String start, String end, List<Node> route) {
        if (start.equals(end)) {
            pathNumber++;
            System.out.println(route);
            return 0;
        }

        Node startNode = findNodeByName(findInNodes, start);
        route.add(startNode);

        for (int i = 0; i < startNode.getNeighborNodes().size(); i++) {
            Node n = startNode.getNeighborNodes().get(i);
            String next = n.getName();
            if (!(next.equals(next.toLowerCase(Locale.ROOT)) && countNodeByName(route, next))) {
                findPath2(findInNodes, next, end, new ArrayList<>(route));
            }
        }
        return 1;
    }

    private void readFromLines(List<String> lines) {
        for (String line : lines) {
            String start = line.split("-")[0];
            String end = line.split("-")[1];
            createChildNodes(start, end);
            createChildNodes(end, start);
        }
    }

    private void createChildNodes(String start, String end) {
        Node startNode = findNodeByName(nodes, start);
        Node endNode = findNodeByName(nodes, end);
        if (startNode == null) {
            startNode = new Node(start);
            nodes.add(startNode);
        }

        if (endNode == null) {
            endNode = new Node(end);
            nodes.add(endNode);
        }
        startNode.add(endNode);
    }

    private Node findNodeByName(List<Node> findInNodes, String nodeToFind) {
        for (Node node : findInNodes) {
            if (node.isNameSame(nodeToFind)) {
                return node;
            }
        }
        return null;
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
        return lines;
    }
}
