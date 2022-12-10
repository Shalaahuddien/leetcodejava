public class RandomizedSet {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> arr = new ArrayList<>();
    Random random = new Random();

    /**Initialize your data structure here. */
    public RandomizedSet() {
        
    }


    /**
    
    Insert a value to the set. returns true if the set did not already contain the specified element.

     */
    public boolean insert(int val) {
        if (map.containsKey(val))
        return false;
        map.put(val, arr.size());
        arr.add(val);
        return true;
    }
    
    /**
    
    removes a value from the set. returns true if the set contained the specified element
    
     */
    public boolean remove(int val) {
        if (!map.containsKey(val))
             return false;

        int index = map.get(val);

        if (index != arr.size() - 1)
        {
            int tailVal = arr.get(arr.size() - 1);
            int currentVal = arr.get(index);
            arr.set(index, tailVal);
            arr.remove(arr.size() - 1);
            map.remove(currentVal);
            map.put(tailVal, index);
        }
        else
        {
            int tailVal = arr.remove(arr.size() - 1);
            map.remove(tailVal);
        }
        return true;
    }
    
    public int getRandom() {
        int randomIndex = random.nextInt(arr.size());
        return arr.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */