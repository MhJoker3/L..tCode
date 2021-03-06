// Created by Rym on 10/20/2016

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class Solution {
    // do not use heap
    public int minMeetingRooms(Interval[] intervals) {
        int len = intervals.length;
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        int earliestEnd = 0;
        for (int i = 0; i < len; i++) {
            if (start[i] < end[earliestEnd]) {
                rooms++;
            } else {
                earliestEnd++;
            }
        }
        return rooms;
    }
    // use heap
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
                @Override
                public int compare(Interval i1, Interval i2) {
                    return i1.start - i2.start;
                }
            });
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
                @Override
                public int compare(Interval i1, Interval i2) {
                    return i1.end - i2.end;
                }
            });
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval earliestEnd = heap.poll();
            if (earliestEnd.end <= intervals[i].start) {
                earliestEnd.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(earliestEnd);
        }
        return heap.size();
    }
    // follow up: output the time arrangement for each room:
    // for (Interval i : heap) {...}
}
