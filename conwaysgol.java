package GUI.Conway;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class conwaysgol {
	JButton b[] = new JButton[2304];
	JButton s = new JButton("Start");
	JButton st = new JButton("Stop");
	JButton cls = new JButton("Clear");
	JTextField tf = new JTextField("50");
	Color bl = Color.BLACK;
	Color w = Color.WHITE;
	Thread thread1;
	int speed;
	public void advance() {
		int c = 0;
		int ssq[] = {-49,-48,-47,-1,1,47,48,49};
		int n[] = new int[2304];
		while(c < 2304) {
			int d;
			if(b[c].getBackground() == bl) {
				d = 0;
				while(d < 8) {
					if(c%48 == 0) {
						if(ssq[d] == -49 || ssq[d] == -1 || ssq[d] == 47) {
							d++;
							continue;
						}
					}
					if(c%48 == 47) {
						if(ssq[d] == 49 || ssq[d] == 1 || ssq[d] == -47) {
							d++;
							continue;
						}
					}
					if(c >= 0 && c < 48) {
						if(ssq[d] < -1) {
							d++;
							continue;
						}
					}
					if(c >= (48*47) && c < 2304) {
						if(ssq[d] > 1) {
							d++;
							continue;
						}
					}
					if(b[c+ssq[d]].getBackground() == bl) {
						n[c]++;
					}
					d++;
				}
			}
			else {
				d = 0;
				while(d < 8) {
					if(c%48 == 0) {
						if(ssq[d] == -49 || ssq[d] == -1 || ssq[d] == 47) {
							d++;
							continue;
						}
					}
					if(c%48 == 47) {
						if(ssq[d] == 49 || ssq[d] == 1 || ssq[d] == -47) {
							d++;
							continue;
						}
					}
					if(c >= 0 && c < 48) {
						if(ssq[d] < -1) {
							d++;
							continue;
						}
					}
					if(c >= (48*47) && c < 2304) {
						if(ssq[d] > 1) {
							d++;
							continue;
						}
					}
					if(b[c+ssq[d]].getBackground() == bl)
						n[c]++;
					d++;
				}
			}
			c++;
		}
		c = 0;
		while(c < 2304) {
			if(n[c] < 2)
				b[c].setBackground(w);
			else if(n[c] > 3)
				b[c].setBackground(w);
			else if(n[c] == 3 && b[c].getBackground() == w)
				b[c].setBackground(bl);
			c++;
		}
	}
	public static void main(String[] ar) {
		new conwaysgol();
	}
	public conwaysgol() {
		JFrame f = new JFrame("Conway's Game of Life");
		f.setSize(1090, 1020);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		p.setLayout(null);
		int c = 0;
		int x = 10, y = 10;
		s.setBounds(975, 350, 100, 50);
		s.setFont(new Font("Ariel", Font.PLAIN, 25));
		p.add(s);
		s.addActionListener(new Actionlistener());
		st.setBounds(975, 410, 100, 50);
		st.setFont(new Font("Ariel", Font.PLAIN, 25));
		st.addActionListener(new Actionlistener());
		p.add(st);
		cls.setBounds(975, 470, 100, 50);
		cls.setFont(new Font("Ariel", Font.PLAIN, 25));
		cls.addActionListener(new Actionlistener());
		p.add(cls);
		tf.setBounds(975, 530, 100, 50);
		tf.setFont(new Font("Ariel", Font.PLAIN, 25));
		p.add(tf);
		while(c < 2304) {
			b[c] = new JButton("");
			b[c].setBackground(Color.WHITE);
			b[c].setBounds(x, y,20,20);
			x+=20;
			if(x > 960) {
				x = 10;
				y+=20;
			}
			b[c].addActionListener(new Actionlistener());
			p.add(b[c++]);
		}
		f.add(p);
		f.setVisible(true);
	}
	private class Actionlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int c = 0;
			if(e.getSource() == s) {
				if(thread1 == null) {
					thread1 = new Thread(new Runnable() {
						public void run(){
							speed = Integer.parseInt(tf.getText());
							while(true){
								advance();
								try {
									Thread.sleep(speed);
								}
								catch(InterruptedException e) {
									break;
								}
							}
						}
				        });
				thread1.start();
				}
				else
					JOptionPane.showMessageDialog(null, "Cannot create multiple threads","Already Running!",JOptionPane.WARNING_MESSAGE);
			}
			else if (e.getSource() == st) {
				thread1.interrupt();
			}
			else if(e.getSource() == cls) {
				int d = 0;
				while(d < 2304)
					b[d++].setBackground(w);
			}
			else {
				while(c < 2304) {
					if(e.getSource() == b[c]) {
						if(b[c].getBackground() == bl)
							b[c].setBackground(w);
						else
							b[c].setBackground(bl);
					}
					c++;
				}
			}
		}
	}
}