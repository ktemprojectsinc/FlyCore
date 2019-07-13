package TestCorePlugin;

import me.Ktem.Core.Types.Player;//Импорт игрока(CorePlayer)
import me.Ktem.Core.api.Command;//Импорт команд
import me.Ktem.Core.api_plugin.Plugin;//Импорт главного класса
import me.Ktem.Ktem_Lang.Ktem_Group;//Импорт групп

public class TestClass extends Plugin  { //Plugin обозначает главный класс плагина
	
	@Override
	public void onEnable(){//Включение плагина
		this.enableCommands();//Включение команд
	}
	
	private void enableCommands(){//Метод включения команд
		//Обычная команда без минимальной группы доступная для всех:
    	new Command("testcommand") {
    		@Override
            public void executeto(final Player player, final String[] args) {//Перезапись abstract метода, Player это CorePlayer
            	player.sendMessage("Command tested");//Сообщение команды
            	return;
            }
    	};
    	//Команда для выполнения которой нужна минимальная группа:
    	Command Command=new Command("testcommandgroup") {//Инициализация переменной Command для последующего использования
            public void executeto(final Player player, final String[] args) {
            	player.sendMessage("Command minimal group "+this.getMinimalGroup().getNameEn()+"§r tested");//this получает класс Command а с помощью getMinimalGroup получаем минимальную группу
            	return;
            }
    	};Command.setMinimalGroup(Ktem_Group.GOLD);//Установка минимальной группы для команды, в данном случае это GOLD
	}

}
