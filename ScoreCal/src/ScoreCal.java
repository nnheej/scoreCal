import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScoreCal extends JFrame implements ActionListener {
	JLabel tit;
	JLabel subjectJL; // 과목
	JLabel scoreJL; // 점수
	JLabel creditJL; // 학점

	JTextField[] subArr = new JTextField[12];
	JComboBox[] scoreArr = new JComboBox[12];
	JComboBox[] creditArr = new JComboBox[12];

	JPanel cp; /* subarr scorearr creditarr을 담을 판 */
	JPanel sp; /* result 를 담을 판 */
	JPanel np; // north panel

	JButton resultB; // 결과 Button
	JTextField resultTF; // 결과 text field
	JTextField cResultTF; // 결과 text field credit 총점

	ScoreCal(String title) {
		super(title);

		/* north panel 설정 */
		np = new JPanel(new GridLayout(2, 3, 10, 10));
		tit = new JLabel("SSU 학점계산기");
		tit.setFont(new Font("나눔고딕", 1, 20));
		tit.setForeground(new Color(5, 45, 83));
		tit.setOpaque(true);
		np.add(new JLabel(""));
		np.add(new JLabel(""));
		np.add(new JLabel(""));
		np.add(new JLabel(""));
		np.add(tit);
		np.add(new JLabel(""));

		/* Ceter Panel 설정 */
		subjectJL = new JLabel("과목");
		scoreJL = new JLabel("점수");
		creditJL = new JLabel("학점");

		JLabel[] labelArr = { subjectJL, scoreJL, creditJL }; //JLabel 폰트변경 
		for (int i = 0; i < 3; i++) {
			labelArr[i].setFont(new Font("나눔고딕", 1, 15));
			labelArr[i].setOpaque(true);
		}

		String[] scores = { "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0",
				"C-", "D+", "D0", "D-", "F" };
		Integer[] credits = { 3, 2, 1 };

		/* 과목 점수 학점 표 판넬 */
		cp = new JPanel(new GridLayout(13, 3));
		cp.add(subjectJL);
		cp.add(scoreJL);
		cp.add(creditJL);

		for (int i = 0; i < 12; i++) {
			subArr[i] = new JTextField(10);
			scoreArr[i] = new JComboBox(scores);
			creditArr[i] = new JComboBox(credits);
			cp.add(subArr[i]);
			cp.add(scoreArr[i]);
			cp.add(creditArr[i]);
		}

		/* South Panel 설정 */
		resultB = new JButton("결과");
		resultTF = new JTextField(15);
		cResultTF = new JTextField(5);
		resultTF.setEditable(false);
		cResultTF.setEditable(false);
		sp = new JPanel();
		sp.setLayout(new FlowLayout());
		sp.add(resultB);
		sp.add(resultTF);

		sp.add(new JLabel("총"));
		sp.add(cResultTF);
		sp.add(new JLabel("학점"));

		/* Event Handling */
		resultB.addActionListener(this);

		/* result panel -South */
		setLayout(new BorderLayout(20, 20)); // 얘를 main에다 설정하면 안됨 왜일까?
		add(np, "North");
		add(cp, "Center");
		add(sp, "South");
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int cnt = 0;
		double total = 0;
		int creditTotal = 0;
		double resultScore = 0;
		double[] scores1 = { 4.5, 4.3, 4.0, 3.5, 3.3, 3.0, 2.5, 2.3, 2.0, 1.5,
				1.3, 1.0, 0 };
		for (int i = 0; i < 12; i++) { // 모든 점수와 학점을 가져와서 학점계산!

			if (!subArr[i].getText().equals("")) {
				int score = scoreArr[i].getSelectedIndex();
				Integer credit = (Integer) creditArr[i].getSelectedItem();
				total += scores1[score] * credit;
				creditTotal += credit;
			}
		}
		resultScore = total / (double) creditTotal;
		resultScore = Math.round(resultScore * 100.0) / 100.0;
		resultTF.setText(resultScore + "");
		cResultTF.setText(creditTotal + "");

	}

	public static void main(String args[]) {
		ScoreCal sc = new ScoreCal("숭실대 학점계산기");
		sc.setSize(500, 550);
		sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sc.setVisible(true);

	}
}
