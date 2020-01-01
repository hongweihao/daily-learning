package mkii.finger1;

/**
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {
    // 需要一个string[] 和一个string来存中间结果
    public String ReverseSentence(String str) {
        if (str == null || "".equals(str.trim())){
            return str;
        }
        StringBuilder builder = new StringBuilder();
        String[] words = str.split(" ");
        for (int i = words.length - 1; i >= 0; i--){
            builder.append(words[i]).append(" ");
        }
        return builder.toString().trim();
    }

    public String reverseSentence(String str) {
        StringBuilder builder = new StringBuilder();
        int p = str.length() - 1;
        int q = p;
        for (int i = p; i >= 0; i--){
            if (str.charAt(i) != ' '){
                while (q > 0 && str.charAt(--q) != ' ');
                for (int j = q + 1; j >= 0 && j <= p; j++){
                    builder.append(str.charAt(j));
                }
                p = q - 1;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        ReverseSentence reverseSentence = new ReverseSentence();
        //String str = reverseSentence.ReverseSentence("student. a am i");
        String str1 = reverseSentence.reverseSentence("student. a am i");
        //System.out.println(str);
        System.out.println(str1);
    }

}
