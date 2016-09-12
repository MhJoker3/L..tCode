public class Solution {
    /**
     * @param digits a number represented as an array of digits
     * @return the result
     */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0 && carry > 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        
        if (carry == 0) {
            return digits;
        }
        
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            ans[i+1] = digits[i];
        }
        return ans;
    }
}
