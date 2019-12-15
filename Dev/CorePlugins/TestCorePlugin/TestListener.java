package TestCorePlugin;

import me.Ktem.Core.api_event.Ktem_Handler;
import me.Ktem.Core.api_events.PlayerChatEvent;
import me.Ktem.Core.api_events.PlayerQuitEvent;
import me.Ktem.Core.api_plugin.Listener;

public class TestListener implements Listener {
	
	//Все Event есть в https://github.com/ktemprojectsinc/FlyCore/blob/master/Dev/FAQ.yml
	
	@Ktem_Handler
	public void onChat(final PlayerChatEvent e){//Event который отправляется при написании текста в чате
		
		final String msg=e.getMessage();//getMessage - Возвращает сообщение написанное игроком
		if(msg.equalsIgnoreCase("lang")){
			e.getPlayer().sendMessage("§fВы написали "+msg);//getPlayer - игрок
			e.setMessage("lang lang");//setMessage заменяет сообщение на новое
		}
		if(msg.equalsIgnoreCase("lang2")){
			e.getPlayer().sendMessage("§fВы написали "+msg);
			e.setCancelled(true);//Отменение сообщения
		}
		
	}
	
	@Ktem_Handler
	public void onQuit(final PlayerQuitEvent e){//Event который отправляется при выходе игрока
		
		e.getPlayer().sendMessage("Пока!");
		
	}

}
