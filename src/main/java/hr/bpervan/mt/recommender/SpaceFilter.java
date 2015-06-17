package hr.bpervan.mt.recommender;

import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.space.Dijkstra;
import hr.bpervan.mt.space.Graph;
import hr.bpervan.mt.space.Node;
import hr.bpervan.mt.space.SPAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Branimir on 17.6.2015..
 */
public class SpaceFilter implements RecommendationAlgorithm {

    private Graph layout;

    public SpaceFilter(Graph layout){
        this.layout = layout;
    }

    @Override
    public double getPrediction(User user, Item item) {
        return 0;
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        return null;
    }

    /** Ovaj glupi graf nije prilagoðen da radi na ovom principu jer svaki èvor nosi
     * udaljenost samo od jednog èvora
     * */
    public List<ItemPredictionLink> getTopNForUser(User user, int n, int location){
        List<ItemPredictionLink> helperList = new ArrayList<>();

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.calculateDistances(layout, location + "");
        List<Node> nodeList = layout.getGraphNodes();

        Collections.sort(nodeList);
        double maxDistance = nodeList.get(nodeList.size() - 1).getDistance();
        for(Node node : nodeList){
            helperList.add(new ItemPredictionLink(
                    Integer.parseInt(node.getNodeName()),
                    1 - ((double)node.getDistance() / maxDistance)
            ));
        }

        Collections.sort(helperList, Collections.reverseOrder());
        return helperList.subList(0, n);
    }
}
