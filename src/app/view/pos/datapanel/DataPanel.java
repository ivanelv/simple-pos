package app.view.pos.datapanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.view.pos.Cart;

public class DataPanel extends ObservableTransactionPanel implements IDataPanel
{
	private JTable table;

	public DataPanel(Cart transaction)
	{
		super(transaction);

		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(getMainTable());

		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane, BorderLayout.CENTER);

		TransferHandler handler = new CartTransferHandler(this);
		this.setTransferHandler(handler);
	}

	@Override
	public void update()
	{
		DefaultTableModel data = subscribedSubject.getData();
		setTableView(data);
	}

	@Override
	public JTable getMainTable()
	{
		if (table == null)
		{
			table = new JTable();
			
			table.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_DELETE)
					{
						int row = getMainTable().getSelectedRow();
						subscribedSubject.getData().removeRow(row);
					}
				}

			});

			setTableView(subscribedSubject.getData());
		}

		return table;
	}

	@Override
	public void setTableView(DefaultTableModel data)
	{
		this.table.setModel(data);

		if (data.getRowCount() == 0)
			table.removeColumn(table.getColumnModel().getColumn(0));
	}
}
