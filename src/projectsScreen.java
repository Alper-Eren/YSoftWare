import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class projectsScreen extends JFrame {

	private JPanel contentPane;
	Connection conn;
	Statement s ;

	public projectsScreen() {
		try {
			  conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ysoft", "postgres", "1234");
	          s = conn.createStatement();
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Veritabanına Bağlanırken Hata Oluştu!!", "Hata", JOptionPane.ERROR_MESSAGE);
	    }
		setTitle("YSoft Yazılım Evi");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 50, 700, 700);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon backgroundMain = new ImageIcon(this.getClass().getResource("/background.png"));
		JLabel backgroundLabel = new JLabel(backgroundMain);
		backgroundLabel.setBounds(0,0,700,700);
		
		contentPane.add(backgroundLabel);
		
		JLabel txtYsoftYazlmEvi = new JLabel();
		txtYsoftYazlmEvi.setForeground(new Color(0, 0, 0));
		txtYsoftYazlmEvi.setText("YSOFT YAZILIM EVİ");
		txtYsoftYazlmEvi.setHorizontalAlignment(SwingConstants.CENTER);
		txtYsoftYazlmEvi.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		txtYsoftYazlmEvi.setBounds(50, 100, 293, 35);
		txtYsoftYazlmEvi.setOpaque(false);
		backgroundLabel.add(txtYsoftYazlmEvi);
		
		JButton backButton = new JButton("<");
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBounds(0, 0, 74, 47);
		backgroundLabel.add(backButton);
		backButton.setBackground(new Color(248, 248, 255));
		backButton.setFocusPainted(false);
		backButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreen mainscrn = new mainScreen();
				mainscrn.setVisible(true);
				setVisible(false);
			}
		});
		
		JLabel chooseTable = new JLabel("Proje İşlemleri :");
		chooseTable.setHorizontalAlignment(SwingConstants.LEFT);
		chooseTable.setFont(new Font("Arial", Font.PLAIN, 20));
		chooseTable.setBounds(55, 150, 160, 60);
		backgroundLabel.add(chooseTable);
		
		JComboBox chooseBox = new JComboBox();
		chooseBox.setBackground(new Color(255, 255, 255));
		chooseBox.setModel(new DefaultComboBoxModel(new String[] {"Seçiniz...", "Aktif Projeleri Göster", "Aktif Olmayan Projeleri Göster", "Yeni Proje Başlat"}));
		chooseBox.setFont(new Font("Arial", Font.PLAIN, 20));
		chooseBox.setBounds(220, 165, 300, 30);
		backgroundLabel.add(chooseBox);
		
		
		DefaultTableModel tableModel = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		
		JButton chooseButton = new JButton("Onayla");
		chooseButton.setBackground(new Color(248, 248, 255));
		chooseButton.setFocusPainted(false);
		chooseButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		chooseButton.setFont(new Font("Arial", Font.PLAIN, 20));
		chooseButton.setHorizontalAlignment(SwingConstants.CENTER);
		chooseButton.setBounds(520, 165, 80, 30);
		backgroundLabel.add(chooseButton);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chooseBox.getSelectedItem().toString() == "Seçiniz...") {
					JOptionPane.showMessageDialog(null, "Lütfen Seçim Yapınız!!", "Hata", JOptionPane.ERROR_MESSAGE);
				}else if(chooseBox.getSelectedItem().toString() == "Aktif Projeleri Göster") {
					chooseBox.setEnabled(false);
					chooseButton.setVisible(false);
					
					JScrollPane activeProjectsScrollPane = new JScrollPane();
					activeProjectsScrollPane.setBounds(50, 200, 600, 400);
					backgroundLabel.add(activeProjectsScrollPane);
					
					JTable activeProjectsTable = new JTable();
					activeProjectsTable.setFont(new Font("Arial", Font.PLAIN, 10));
					activeProjectsTable.getTableHeader().setReorderingAllowed(false);
					activeProjectsTable.setRowSelectionAllowed(true);
					activeProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					activeProjectsScrollPane.setViewportView(activeProjectsTable);
					activeProjectsTable.setModel(tableModel);
					
					String queryProjects= "SELECT project.project_id ,project_name,employee.name,number_of_analyst,number_of_designer,number_of_programmer FROM project LEFT JOIN employee on manager_id=identification_number WHERE is_project_active = 'true' ";
					try {
						ResultSet r = s.executeQuery(queryProjects);
						ResultSetMetaData rsmd = r.getMetaData();
						DefaultTableModel  model =  (DefaultTableModel)activeProjectsTable.getModel();
						int cols = rsmd.getColumnCount();
						String[] colName = new String[cols];
						for(int i=0; i<cols; i++)
							colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String  name, surname,salary,projectID,hireDateTime,projectid;
						while(r.next()) {
							projectid = r.getString(1);
							name = r.getString(2);
							surname  = r.getString(3);
							salary  = r.getString(4);
							projectID  = r.getString(5);
							hireDateTime = r.getString(6);
							
							String[] row = {projectid,name,surname,salary,projectID, hireDateTime};
							model.addRow(row);
						}
						JButton finishProjectButton = new JButton("Projeyi Bitir");
						backgroundLabel.add(finishProjectButton);
						finishProjectButton.setVisible(true);
						finishProjectButton.setBackground(new Color(248, 248, 255));
						finishProjectButton.setFocusPainted(false);
						finishProjectButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
						finishProjectButton.setFont(new Font("Arial", Font.PLAIN, 20));
						finishProjectButton.setHorizontalAlignment(SwingConstants.CENTER);
						finishProjectButton.setBounds(230, 610, 200, 40);
						finishProjectButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								
								int selectedRow = activeProjectsTable.getSelectedRow();
								if(selectedRow == -1) {
									JOptionPane.showMessageDialog(null, "Proje Seçilmedi!", "Hata", JOptionPane.ERROR_MESSAGE);
								}else {
									YSoft ysoft = new YSoft();
									String prId = model.getValueAt(selectedRow, 0).toString();
									try {
										ysoft.finishProject(prId);
										
                                        JOptionPane.showMessageDialog(null, "Proje Başarıyla Bitirildi!!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                                        s.close();
                                        conn.close();
                                        mainScreen mainscrn = new mainScreen();
                                        mainscrn.setVisible(true);
                                        setVisible(false);
										
	
										
									}catch (Exception e) {
										// TODO: handle exception
										JOptionPane.showMessageDialog(null, "Proje Bitirilemedi!!", "Hata", JOptionPane.ERROR_MESSAGE);
									}
									
			
									
								}
							}
						});
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
						
				}else if(chooseBox.getSelectedItem().toString() == "Aktif Olmayan Projeleri Göster") {
					chooseBox.setEnabled(false);
					chooseButton.setVisible(false);
					
					JScrollPane noActiveProjectsScrollPane = new JScrollPane();
					noActiveProjectsScrollPane.setBounds(50, 200, 600, 400);
					backgroundLabel.add(noActiveProjectsScrollPane);
					
					JTable noActiveProjectsTable = new JTable();
					noActiveProjectsTable.setFont(new Font("Arial", Font.PLAIN, 10));
					noActiveProjectsScrollPane.setViewportView(noActiveProjectsTable);
					noActiveProjectsTable.getTableHeader().setReorderingAllowed(false);
					noActiveProjectsTable.setRowSelectionAllowed(true);
					noActiveProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					noActiveProjectsTable.setModel(tableModel);

					String queryProjects= "SELECT project.project_id,project_name,employee.name as manager_name,number_of_analyst,number_of_designer,number_of_programmer FROM project LEFT JOIN employee on manager_id=identification_number WHERE is_project_active = 'false' ";
					try {
						ResultSet r = s.executeQuery(queryProjects);
						ResultSetMetaData rsmd = r.getMetaData();
						DefaultTableModel  model =  (DefaultTableModel)noActiveProjectsTable.getModel();
						int cols = rsmd.getColumnCount();
						String[] colName = new String[cols];
						for(int i=0; i<cols; i++)
							colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String emp_name , name,salary,projectID,hireDateTime,projectid;
						while(r.next()) {
							projectid = r.getString(1);
							name = r.getString(2);
							emp_name  = r.getString(3);
							salary  = r.getString(4);
							projectID  = r.getString(5);
							hireDateTime = r.getString(6);
							String[] row = {projectid,name,emp_name,salary,projectID, hireDateTime};
							model.addRow(row);
						}
						JButton activeButton = new JButton("Projeyi Aktif Et");
						backgroundLabel.add(activeButton);
						activeButton.setVisible(true);
						activeButton.setBackground(new Color(248, 248, 255));
						activeButton.setFocusPainted(false);
						activeButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
						activeButton.setFont(new Font("Arial", Font.PLAIN, 20));
						activeButton.setHorizontalAlignment(SwingConstants.CENTER);
						activeButton.setBounds(230, 610, 200, 40);
						activeButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								
								int selectedRow = noActiveProjectsTable.getSelectedRow();
								if(selectedRow == -1) {
									JOptionPane.showMessageDialog(null, "Proje Seçilmedi!", "Hata", JOptionPane.ERROR_MESSAGE);
								}else {
									YSoft ysoft = new YSoft();
									String prId = model.getValueAt(selectedRow, 0).toString();
									boolean activated = ysoft.makeProjectActive(prId);
									
									if(activated) {
										JOptionPane.showMessageDialog(null, "Proje Aktif Edildi!!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
										try {
                                            s.close();
                                            conn.close();
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        mainScreen mainscrn = new mainScreen();
                                        mainscrn.setVisible(true);
                                        setVisible(false);
									}else {
										JOptionPane.showMessageDialog(null, "Proje Gerekli Koşulları Sağlamıyor!!", "Hata", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						});
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
						
				}else if(chooseBox.getSelectedItem().toString() == "Yeni Proje Başlat") {
						chooseBox.setEnabled(false);
						chooseButton.setVisible(false);
						
						JLabel projectNameLabel = new JLabel("Proje İsmi :");
						backgroundLabel.add(projectNameLabel);
						projectNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						projectNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						projectNameLabel.setBounds(20, 200, 250, 60);
						
						
						JLabel minAnalystLabel = new JLabel("Minimum Analizci Sayısı :");
						backgroundLabel.add(minAnalystLabel);
						minAnalystLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						minAnalystLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						minAnalystLabel.setBounds(20, 250, 250, 60);
						
						
						JLabel minDesignerLabel = new JLabel("Minimum Tasarımcı Sayısı :");
						backgroundLabel.add(minDesignerLabel);
						minDesignerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						minDesignerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						minDesignerLabel.setBounds(20, 300, 250, 60);
						
						
						JLabel minProgrammerLabel = new JLabel("Minimum Programcı Sayısı :");
						backgroundLabel.add(minProgrammerLabel);
						minProgrammerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						minProgrammerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						minProgrammerLabel.setBounds(20, 350, 250, 60);
						
						JLabel maxAnalystLabel = new JLabel("Maksimum Analizci Sayısı :");
						backgroundLabel.add(maxAnalystLabel);
						maxAnalystLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						maxAnalystLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						maxAnalystLabel.setBounds(20, 400, 250, 60);
						
						
						JLabel maxDesignerLabel = new JLabel("Maksimum Tasarımcı Sayısı :");
						backgroundLabel.add(maxDesignerLabel);
						maxDesignerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						maxDesignerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						maxDesignerLabel.setBounds(20, 450, 250, 60);
						
						
						JLabel maxProgrammerLabel = new JLabel("Maksimum Programcı Sayısı :");
						backgroundLabel.add(maxProgrammerLabel);
						maxProgrammerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						maxProgrammerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
						maxProgrammerLabel.setBounds(20, 500, 250, 60);
						
						JTextField projectNameField = new JTextField();
						backgroundLabel.add(projectNameField);
						projectNameField.setFont(new Font("Arial", Font.PLAIN, 18));
						projectNameField.setBounds(280, 215, 180, 30);
						projectNameField.setColumns(10);
						
						JTextField minAnalystField = new JTextField();
						backgroundLabel.add(minAnalystField);
						minAnalystField.setFont(new Font("Arial", Font.PLAIN, 18));
						minAnalystField.setBounds(280, 265, 180, 30);
						minAnalystField.setColumns(10);
						
						JTextField minDesignerField = new JTextField();
						backgroundLabel.add(minDesignerField);
						minDesignerField.setFont(new Font("Arial", Font.PLAIN, 18));
						minDesignerField.setBounds(280, 315, 180, 30);
						minDesignerField.setColumns(10);
						
						JTextField minProgrammerField = new JTextField();
						backgroundLabel.add(minProgrammerField);
						minProgrammerField.setFont(new Font("Arial", Font.PLAIN, 18));
						minProgrammerField.setBounds(280, 365, 180, 30);
						minProgrammerField.setColumns(10);
						
						JTextField maxAnalystField = new JTextField();
						backgroundLabel.add(maxAnalystField);
						maxAnalystField.setFont(new Font("Arial", Font.PLAIN, 18));
						maxAnalystField.setBounds(280, 415, 180, 30);
						maxAnalystField.setColumns(10);
						
						JTextField maxDesignerField = new JTextField();
						backgroundLabel.add(maxDesignerField);
						maxDesignerField.setFont(new Font("Arial", Font.PLAIN, 18));
						maxDesignerField.setBounds(280, 465, 180, 30);
						maxDesignerField.setColumns(10);
						
						JTextField maxProgrammerField = new JTextField();
						backgroundLabel.add(maxProgrammerField);
						maxProgrammerField.setFont(new Font("Arial", Font.PLAIN, 18));
						maxProgrammerField.setBounds(280, 515, 180, 30);
						maxProgrammerField.setColumns(10);
					
						JButton activeButton = new JButton("Başlat");
						backgroundLabel.add(activeButton);
						activeButton.setVisible(true);
						activeButton.setBackground(new Color(248, 248, 255));
						activeButton.setFocusPainted(false);
						activeButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
						activeButton.setFont(new Font("Arial", Font.PLAIN, 20));
						activeButton.setHorizontalAlignment(SwingConstants.CENTER);
						activeButton.setBounds(200, 580, 200, 40);
						activeButton.addActionListener( new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								if(projectNameField.getText().isEmpty() || minAnalystField.getText().isEmpty() || maxAnalystField.getText().isEmpty() || minDesignerField.getText().isEmpty() || maxDesignerField.getText().isEmpty() || minProgrammerField.getText().isEmpty() || maxProgrammerField.getText().isEmpty()) {
									JOptionPane.showMessageDialog(null, "Lütfen Tüm Değerleri Doldurunuz!!", "Hata", JOptionPane.ERROR_MESSAGE);
								}else {
									try {
										int minAnalyst = Integer.parseInt(minAnalystField.getText());
										int maxAnalyst = Integer.parseInt(maxAnalystField.getText());
										int minDesigner = Integer.parseInt(minDesignerField.getText());
										int maxDesigner = Integer.parseInt(maxDesignerField.getText());
										int minProgrammer = Integer.parseInt(minProgrammerField.getText());
										int maxProgrammer = Integer.parseInt(maxProgrammerField.getText());
										s.close();
										conn.close();
										YSoft ysoft = new YSoft();
										ysoft.addNewProject(projectNameField.getText(), minAnalyst, minDesigner, minProgrammer, maxAnalyst, maxDesigner, maxProgrammer);
										JOptionPane.showMessageDialog(null, "Proje Eklendi!!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
										
                                        mainScreen mainscrn = new mainScreen();
                                        mainscrn.setVisible(true);
                                        setVisible(false);
									}catch (Exception e) {
										JOptionPane.showMessageDialog(null, "Lütfen Geçerli Değerler Giriniz!!", "Hata", JOptionPane.ERROR_MESSAGE);
									}
								}
								
							}
						});
						
						
					
				}
			}
		});
		
	}
	

}