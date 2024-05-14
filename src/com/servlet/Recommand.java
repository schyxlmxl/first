package com.servlet;
import com.jdbc.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Recommand", value = "/Recommand")
public class Recommand extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        userserver us=new userserver();
        String targetUser=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++) {
            if ("name".equals(cookies[i].getName())) {
                targetUser = cookies[i].getValue();
            }
        }
        //get the customer list
        ArrayList<user> userlist=us.userlist();
        for(int i=userlist.size()-1;i>=0;i--){
            if(userlist.get(i).is_super!=0){
                userlist.remove(i);
            }
        }

        //get the goods that user buy and count the number
        Map<String, Set<Integer>>usertogoods=new HashMap<>();
        Map<String,Integer> num=new HashMap<>();
        pay pay=new pay();
        ArrayList<pay> paylist =pay.paylist();
        for (user user : userlist) {
            Set<Integer> goodslist = new HashSet<>();
            for (pay value : paylist) {
                if (value.payer.equals(user.name)) {
                    goodslist.add(value.id);
                }
            }
            usertogoods.put(user.name, goodslist);
            num.put(user.name, goodslist.size());
        }

        //get the user that goods been bought
        Map<Integer, Set<String>> goodstouser = new HashMap<>();
        for (Map.Entry<String, Set<Integer>> entry : usertogoods.entrySet()) {
            String user = entry.getKey();
            Set<Integer> goods = entry.getValue();
            for (Integer gid : goods) {
                Set<String> users = goodstouser.getOrDefault(gid, new HashSet<>());
                users.add(user);
                goodstouser.put(gid, users);
            }
        }

        //get the number of goods that any two user buy in same
        Map<String, Map<String, Integer>> CFMatrix = new HashMap<>();
        for (Map.Entry<Integer, Set<String>> entry : goodstouser.entrySet()) {
            Integer goods = entry.getKey();
            Set<String> users = entry.getValue();

            for (String u : users) {
                if (!CFMatrix.containsKey(u)) {
                    CFMatrix.put(u, new HashMap<>());
                }
                for (String v : users) {
                    if (!v.equals(u)) {
                        if (!CFMatrix.get(u).containsKey(v)) {
                            CFMatrix.get(u).put(v, 0);
                        }
                        CFMatrix.get(u).put(v, CFMatrix.get(u).get(v) + 1);
                    }
                }
            }
        }

        //culculate the simlarity
        Map<String, Map<String, Double>> sim =new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : CFMatrix.entrySet()) {
            String u = entry.getKey();
            Map<String, Integer> otherUser = entry.getValue();
            for (Map.Entry<String, Integer> userScore : otherUser.entrySet()) {
                String v = userScore.getKey();
                int score = userScore.getValue();
                if(!sim.containsKey(u))
                {
                    sim.put(u,new HashMap<>());
                }
                sim.get(u).put(v, CFMatrix.get(u).get(v) / Math.sqrt(num.get(u) * num.get(v)));
            }
        }

        //score the goods
        Map<Integer, Double> itemRank = new HashMap<>();
        if (usertogoods.containsKey(targetUser)) {
            Set<Integer> items = usertogoods.get(targetUser);
            // 按照相似度进行排序，然后取前 3 个
            List<Map.Entry<String, Double>> sortedSim = new ArrayList<>(sim.get(targetUser).entrySet());
            Collections.sort(sortedSim, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    return Double.compare(o2.getValue(), o1.getValue());
                }
            });


            for (int i = 0; i < 3; i++) {
                //前3个相似度高的用户
                if (i >= sortedSim.size())
                    break;

                String similarUser = sortedSim.get(i).getKey();
                double score = sortedSim.get(i).getValue();

                // 找出相似用户中有交互的物品，但当前用户并未交互过的物品进行推荐
                for (int item : usertogoods.get(similarUser)) {
                    if (usertogoods.get(targetUser).contains(item))//如果用户已经对该物品交互过，就不用再推荐
                        continue;

                    itemRank.put(item, itemRank.getOrDefault(item, 0.0) + score);
                    //这里就得到的推荐候选的一个集合
                }
            }
        }

        // 根据评分进行排序，取排名靠前的 3 个物品作为推荐列表
        List<Map.Entry<Integer, Double>> topNItems = new ArrayList<>(itemRank.entrySet());
        Collections.sort(topNItems, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return Double.compare(o2.getValue(), o1.getValue());
            }
        });

        ArrayList<goods> goodlist=new ArrayList<>();
        for (int i=0;i<Math.min(3, topNItems.size());i++){
            goodsserver gs=new goodsserver();
            goods g=gs.searchgoods(topNItems.get(i).getKey());
            goodlist.add(g);
        }
        request.setAttribute("goods",goodlist);
        request.getRequestDispatcher("first-page.jsp").include(request,response);
    }
}
