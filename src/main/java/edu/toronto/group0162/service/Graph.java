package edu.toronto.group0162.service;
import edu.toronto.group0162.dao.CardDao;
import edu.toronto.group0162.dao.UserDao;
import edu.toronto.group0162.entity.ENode;
import edu.toronto.group0162.entity.Edge;
import edu.toronto.group0162.entity.Node;
import edu.toronto.group0162.entity.VNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class Graph {

//    @Getter
//    private final UserDao edgeDao;
//
//    private final CardDao nodeDao;

    private static int INF = Integer.MAX_VALUE;

    private int mEdgNum;
    public VNode[] mVexs;


    //建 顶点表，建邻接表
    public Graph(List<Node> nodes, List<Edge> edges) {
        int vlen = nodes.size();
        int elen = edges.size();
        mVexs = new VNode[vlen]; //mVexs建VNode数组，定义好了长度，长度实际就是和你正常点的总数一致
        //第一个循环
        for (int i = 0; i < mVexs.length; i++)
        {
            mVexs[i] = new VNode(); //具体实例化1个VNode
            mVexs[i].data = nodes.get(i).getName(); //VNode的名字和原来节点名字一致
            mVexs[i].firstEdge = null; //首个VNode的邻接边为空
        }
        mEdgNum = elen; //edges 有多少个，总共有多少个边
        //第二个循环
        for (int i = 0; i < elen; i++)
        {
            //每一条 edge基本信息
//            char c1 = edges[i].start;
//            char c2 = edges[i].end;
            double distance =(int) edges.get(i).getDistance();
            //分别取出 Edge边 顶点与终点 其在 VNode数组中所对应的Index 位置
            int p1 = edges.get(i).getStart()-1;
            int p2 = edges.get(i).getStop()-1;
            //注意以下 新建了两个 ENode, 并调用了  linkLast方法 (if/elese判断)
            //两个ENode，是为了双方向
            ENode node1 = new ENode();
            node1.ivex = p2; //用到了 ENode 里的属性 int ivex
            node1.weight = distance;
            if (mVexs[p1].firstEdge == null)
            {
                mVexs[p1].firstEdge = node1;
            }
            else
            {
                linkLast(mVexs[p1].firstEdge, node1);
            }

            ENode node2 = new ENode();
            node2.ivex = p1;
            node2.weight = distance;
            if (mVexs[p2].firstEdge == null)
            {
                mVexs[p2].firstEdge = node2;
            }
            else
            {
                linkLast(mVexs[p2].firstEdge, node2);
            }
        }
    }

    private void linkLast(ENode list, ENode node) {
        ENode p = list;
        while (p.nextEdge != null) p = p.nextEdge;
        p.nextEdge = node;
    }

    //本质干 取出 VNode数组里每一个VNode对应的 下标
    private int getPosition(char ch) {
//        for (int i = 0; i < mVexs.length; i++) if (mVexs[i].data == ch) return i;
        return -1;
    }

    private double getWeight(int start, int end) {

        if (start == end) return 0;

        ENode node = mVexs[start].firstEdge;
        while (node != null) {
            if (end == node.ivex)
                return node.weight;
            node = node.nextEdge;
        }

        return INF;
    }

    //建立最短距离与最短路径矩阵
    public void floyd(int[][] path, double[][] dist) {

        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = getWeight(i, j);
                path[i][j] = j;
            }
        }
        System.out.printf("path1: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) System.out.printf("%2d  ", path[i][j]);
            System.out.printf("\n");
        }
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {

                    double tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {

                        dist[i][j] = tmp;

                        path[i][j] = path[i][k];
                    }
                }
            }
        }

//        System.out.printf("floyd: \n");
//        for (int i = 0; i < mVexs.length; i++) {
//            for (int j = 0; j < mVexs.length; j++) System.out.printf("%2d  ", dist[i][j]);
//            System.out.printf("\n");
//        }
//
//        System.out.printf("\n");
//
//        System.out.printf("path2: \n");
//        for (int i = 0; i < mVexs.length; i++) {
//            for (int j = 0; j < mVexs.length; j++) System.out.printf("%2d  ", path[i][j]);
//            System.out.printf("\n");
//        }
    }

//    public void GetShortestPath(int nStartNodeNo, int nEndNodeNo, int[][] dist, int[][] path,List<Node> nodes)
//    {
int startIndex;
    int endIndex;
    String tripInfo = "";

        public void GetShortestPath(String StartNode, String EndNode, double[][] dist, int[][] path,List<Node> nodes)
        {

            int count = 0;
        Iterator nodesList = nodes.iterator();
        while(nodesList.hasNext()) {
            Node nodeGet = (Node) nodesList.next();
            if (nodeGet.getName().equals(StartNode)){startIndex = count;}
            if (nodeGet.getName().equals(EndNode)){endIndex = count;}
            count ++;
        }




//        System.out.printf("\n%s -> %s shortest distance: %d\n", nodes.get(nStartNodeNo).getName() ,nodes.get(nEndNodeNo).getName(), dist[nStartNodeNo][nEndNodeNo]);
//            System.out.printf("\n%s -> %s shortest distance: %d\n", StartNode ,EndNode, dist[startIndex][endIndex]);
        tripInfo += "\n ->  shortest distance: \n" + " " + StartNode +" "+ EndNode+" "+dist[startIndex][endIndex];

        int k;

//        k = path[nStartNodeNo][nEndNodeNo]; //先找到两点中的第一个邻接点
            k = path[startIndex][endIndex]; //先找到两点中的第一个邻接点

//        System.out.printf("shortest path: %s", nodes.get(nStartNodeNo).getName());

            tripInfo += "\n"+ " shortest path: " +StartNode;

//            System.out.printf("shortest path: %s", StartNode);
        while(k != endIndex)
        {
//            System.out.printf("-> %s",nodes.get(k).getName());
            tripInfo += "->"+" "+nodes.get(k).getName();
            k = path[k][endIndex];
        }

//        System.out.printf("-> %s\n", nodes.get(nEndNodeNo).getName() );
//            System.out.printf("-> %s\n", EndNode );
        tripInfo += "-> "+" "+ EndNode;

        setTripInfo(tripInfo);

    }

    public void setTripInfo(String tripInfo){
            this.tripInfo = tripInfo;
    }

    public String getTripInfo(){
        return this.tripInfo;
    }
}
