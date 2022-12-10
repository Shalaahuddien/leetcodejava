class ListNode
{
    String url;
    ListNode prev;
    ListNode next;

    ListNode()
    {

    }
    ListNode(String url)
    {
        this.url = url;
    }
}

class BrowserHistory {
    private ListNode head, tail;
    private ListNode cur;

    public BrowserHistory(String homepage) {
        head = new ListNode();
        tail = new ListNode();
        cur = new ListNode(homepage);
        insert(head, cur, tail);
    }

    private void insert(ListNode prev, ListNode node, ListNode next)
    {
        prev.next = node;
        node.prev = prev;

        node.next = next;
        next.prev = node;
    }
    
    public void visit(String url) {
        ListNode nextCur = new ListNode(url);
        insert(cur, nextCur, tail);
        cur = nextCur;
    }
    
    public String back(int steps) {
        while (steps > 0 && cur.prev != head)
        {
            cur = cur.prev;
            steps--;
        }
        return cur.url;
    }
    
    public String forward(int steps) {
        while (steps > 0 && cur.next != tail)
        {
            cur = cur.next;
            steps--;
        }
        return cur.url;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */