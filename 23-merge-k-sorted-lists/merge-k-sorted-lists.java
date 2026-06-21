/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
      
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
      
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
      
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
          
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
          
            current.next = smallestNode;
            current = current.next;
        }
      
        return dummyHead.next;
    }
}