package back.sectionC;

import java.util.*;

public class SituationC {


    //  Map<C,Array<N>> - every C node connected to N's array.
    private static Map<Integer, ArrayList<Integer>> C_to_N_Hash = new HashMap<>();
    private static Map<Integer, ArrayList<Integer>> Copy_C_to_N_Hash = new HashMap<>();
    //  Set where we pull out every 2 Integers and
    //  connecting them with every C node.
    private Set<Integer> connected_I_nodes = new HashSet<>();
    //  Map with all sub-graphs that I need.
    private Map<Integer, ArrayList<Integer>> Split_Sub_Graphs = new HashMap<>();
    private int IndependentNodes, Clique;

    public static void main(String[] args) {
        SituationC situationC = new SituationC();
        situationC.CreateGraph(6, 3);
        System.out.println("How much Triangle sub-graphs we need -> " + situationC.getSubGraphTriangles(10, 5));
        System.out.println("How much Sticks-n-Dots sub-graphs we need -> " + situationC.getSubGraphStickAndDots(10, 5));

        situationC.search_All_two_matches(C_to_N_Hash);

//        Map<Integer, String> m = new HashMap<>();
//        m.put(1," ");
//        m.put(2," ");
//        m.put(3," ");
//        m.put(4," ");
//        System.out.println(m.size());


    }


    public void CreateGraph(int IndependentNodes, int Clique) {
        this.IndependentNodes = IndependentNodes;
        this.Clique = Clique;

        for (int k = 0; k < Clique; k++) {
            C_to_N_Hash.put(k, getRandomNumber(IndependentNodes));// putting numbers into each key
            C_to_N_Hash.get(k).sort(Comparator.naturalOrder()); // sort numbers
            System.out.println("C" + k + " ->" + C_to_N_Hash.get(k));

        }

    }


    // Numbers of triangle sub-graphs in graph
    public Integer getSubGraphTriangles(int IndependentNodes, int Clique) {
        return (2 * IndependentNodes - Clique) / 3;
    }

    // Numbers of ticks and dots sub-graphs in graph/
    public Integer getSubGraphStickAndDots(int IndependentNodes, int Clique) {
        return (2 * Clique - IndependentNodes) / 3;
    }

    //    In here we get random numbers between min and max.
    public int Randomizer(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    //    In here I put random numbers in to arraylist and then
    //    return it to method CreateGraph.
    public ArrayList<Integer> getRandomNumber(int max) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random randomGenerator = new Random();
        int sizeOfList = randomGenerator.nextInt(max) + 1;

        while (numbers.size() < sizeOfList) {

            int random = randomGenerator.nextInt(max);

            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        return numbers;
    }

    //    public void search_All_two_matches(Map<Integer, ArrayList<Integer>> C_to_N_Hash) {
    private Map.Entry<Integer, ArrayList<String>> makePair(ArrayList<Integer> object, int key) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < object.size() - 1; i++) {
            for (int j = i + 1; j < object.size(); j++) {
                String s = object.get(i).toString() + "-" + object.get(j).toString();
                arrayList.add(s);
            }
        }
        Map.Entry<Integer, ArrayList<String>> mapOfPairs = new AbstractMap.SimpleEntry<>(key, arrayList);
        return mapOfPairs;
    }

    public void search_All_two_matches(Map<Integer, ArrayList<Integer>> C_to_N_Hash) {
        Map<Integer, ArrayList<String>> mapOfPairs = new HashMap<>();
        for (Integer i = 0; i < C_to_N_Hash.size(); i++) {
            if (C_to_N_Hash.get(i).size() <= 1) {
                System.err.println("NODE C" + i + " CONTAINS ONLY 1 I" + C_to_N_Hash.get(i));
                System.exit(0);
//                break;

            } else {
                System.out.println("||");
                System.out.println(C_to_N_Hash.get(i));
                System.out.println(makePair(C_to_N_Hash.get(i), i));
                mapOfPairs.put(makePair(C_to_N_Hash.get(i), i).getKey(), makePair(C_to_N_Hash.get(i), i).getValue());


            }
        }
        get_all_C(mapOfPairs);
    }

//    public void getting_All_Triangle_sub_graphs(Map<Integer, ArrayList<String>> mapOfPairs) {
//        ArrayList<String> arrayOfTriangles = new ArrayList<>();
//
//        for (int i = 0; i < mapOfPairs.size(); i++) {
//            int current = 0;
//            for (int j = 0; j < mapOfPairs.get(i).size() - 1; j++) {
//                if (arrayOfTriangles.isEmpty()) {
//                    arrayOfTriangles.add(mapOfPairs.get(i).get(j));
//                }
//                for (int k = 0; k < mapOfPairs.get(j + 1).size(); k++) {
//                    if (!arrayOfTriangles.get(j).equals(mapOfPairs.get(j + 1).get(k))) {
//                        arrayOfTriangles.add(mapOfPairs.get(i + 1).get(k));
//                        break;
//                    }
//
//                }
//
//            }
////                mapOfPairs.get(i).get(i).equals()
//
////                setOfTriangles.add(mapOfPairs.get(i).get(current));
////                if (!mapOfPairs.get(i).get(j).equals(mapOfPairs.get(i+1).get(j))) {
////                 setOfTriangles.add()
////                }
////                current++;
////            }
//        }
//
//
//    }


    public void getting_All_Triangle_sub_graphs0(Map<Integer, ArrayList<String>> mapOfPairs) {
        List<String> list = new LinkedList<>(); // set Of Triangles

        int nums = 4;
        Map<Integer, ArrayList<String>> copyOf_mapOfPairs;
        copyOf_mapOfPairs = mapOfPairs;
        int size_of_the_last_array = copyOf_mapOfPairs.get(Clique - 1).size(); // size of the last key at the last if


        for (int i = 0; i <= mapOfPairs.size() - 1; i = i + 2) {

            for (int j = 0; j < mapOfPairs.get(i).size(); j++) {
                String last_value;
                last_value = split_and_put_into_set(list, copyOf_mapOfPairs.get(i).get(j));
                if (list.size() % 2 != 0) {
//                    deleteLast(set);
                    list.remove(last_value);
                    System.out.println(list);
                } else {

                    for (int k = 0; k < mapOfPairs.get(i + 1).size(); k++) {
                        last_value = split_and_put_into_set(list, copyOf_mapOfPairs.get(i + 1).get(k));
                        int sizeNum = list.size();
                        if (sizeNum % 2 != 0) {
//                            deleteLast(set);
                            list.remove(last_value);
                            System.out.println(list);
                        }
                        if (sizeNum == nums) {
//                        j = mapOfPairs.get(i).size() - 1;
                            nums = nums + 4;
                            j = mapOfPairs.get(i).size();
                            break;
                        }

                    }
                }

            }
//            if (set.size() != 2 + (nums - 1) * 2) {
//                deleteAll(set);
//            } else break;
        }

//        if (list.size() % 2 != 0) {
//            for (int t = 0; t < size_of_the_last_array; t++) {
//                split_to_set(list, mapOfPairs.get(mapOfPairs.size()).get(t));
//            }
//        }


        System.out.println(list);

    }


    public void get_all_C(Map<Integer, ArrayList<String>> mapOfPairs) {

        int needed_size_list = IndependentNodes;
        List<String> list = new LinkedList<>();
        Map<Integer, ArrayList<String>> map = mapOfPairs;

        split_and_put_into_set(list, map.get(0).get(0));
        for (int i = 0; i < map.get(0).size(); i++) {
            int k = 0;
            int cur_size = 2;
            if (list.isEmpty()) {
                split_and_put_into_set(list, map.get(0).get(i));

            }
            for (int j = 0; j < map.get(k + 1).size(); j++) {
                split_and_put_into_set(list, map.get(k + 1).get(j));
//                String last_value = list.get(list.size()-1);
                if (list.size() % 2 != 0) {
                    deleteLast(list);
                } else if (list.size() == cur_size + 2) {
                    cur_size = cur_size + 2;
                    k++;

                }


                if (list.size() == needed_size_list) {
                    System.out.println("We founded what we needed! -> " + list);
                    i = map.get(0).size();
                    break;
                }

            }
            if (list.size() == cur_size) {
                deleteAll(list);
            }

        }


    }


    public String split_and_put_into_set(List<String> list, String s) {
//        String s;
        String[] splitString = s.split("-");
//        arrayOfTriangles.get(index).split("\\|",2);
        if (!list.contains(splitString[0])) {
            list.add(splitString[0]);
        }
        if (!list.contains(splitString[1])) {
            list.add(splitString[1]);
        }
        System.out.println(list);

        return list.get(list.size() - 1);
    }

    public void deleteLast(List<String> list) {
        list.remove(list.get(list.size() - 1));
        System.out.println(list);
    }

    public Set<String> deleteLastTwo(Set<String> set) {
        int i = 0;
        while (i != set.size()) {
            i++;
        }
        set.remove(i);
        set.remove(i - 1);
        set.removeAll(set);
        return set;
    }

    public void deleteAll(List<String> list) {
        list.clear();
        System.out.println(list + "<- тут ничего не должно быть");
    }

}

//    public  put_all_2_matches_in_every_Copy_Map


//    public void createObject(ArrayList<Integer> arrayOfI) {
//        Set<ArrayList<Integer>> set = new HashSet<>();
//        set.add(arrayOfI);
//    }



