1class Solution {
2    public int calPoints(String[] operations) {
3        List<Integer> ls = new ArrayList<>();
4
5        for (String op : operations) {
6
7            if (Character.isDigit(op.charAt(0)) || (op.charAt(0) == '-' && op.length() > 1)) {
8                ls.add(Integer.parseInt(op));
9            }
10            else if (op.equals("D")) {
11                ls.add(ls.get(ls.size() - 1) * 2);
12            }
13            else if (op.equals("C")) {
14                ls.remove(ls.size() - 1);
15            }
16            else if (op.equals("+")) {
17                int size = ls.size();
18                ls.add(ls.get(size - 1) + ls.get(size - 2));
19            }
20        }
21
22        int sum = 0;
23        for (int n : ls) sum += n;
24        return sum;
25    }
26}
27