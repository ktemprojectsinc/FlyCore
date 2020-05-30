package TestCoreModule;//Название пакета

import me.Ktem.Core.api.*;//Импорт обшего api
import me.Ktem.Core.api_plugin.*;//Импорт api plugin
import me.Ktem.Ktem_Lang.Ktem_Group;//Импорт групп
import me.Ktem.Ktem_Modules.Ktem_Module;//Модуль

public class TestClass extends Ktem_Module  { //Наследовать класс модуля
	
	public TestClass(){
		super("TestModule","1.0","Ktem");//Название/Версия/Автор
	}
	
	@Override
	public void enableModule(){//Включение модуля
		this.enableCommands();//Регистрация команд
		this.newListener((Listener)new TestListener());//Регистрация Listener
	}
	
	private void enableCommands(){//Метод регистрации команд
		//Обычная команда без минимальной группы доступная для всех:
		this.newCmd("testcommand","testcmd")//Устанавливается имя команды, а также её алиасы (Не ограничено) / Наследуется, для привязки к текущему модулю
			.setAction((gamer,player,cmd,args)->{//Выполнение команды
				player.sendMessage("Command "+cmd+" tested");//Сообщение команды
			})
			.hide()//Скрытие команды и её алиасов из таб листа для подбора команд
			.create();
    	//Команда для выполнения которой нужна минимальная группа:
    	this.newCmd("testcommandgroup","testcmdgroup")
    		.setMinimalGroup(Ktem_Group.VIP)//Установка минимальной группы для команды, в данном случае это CLAY
    		.setAction((gamer,player,args)->{
    			player.sendMessage("Command minimal group "+Ktem_Group.VIP.getNameEn()+"§r tested");
    		})
    		.create();
    	this.newCmd("testcommandperm","testcmdperm")
			.setMinimalGroup(Ktem_Group.YOUTUBE)//Установка минимальной группы для команды, в данном случае это YouTube
			.setOnlyGroup(Ktem_Group.PREMIUM)//Установка индивидуальной группы, только этой группе будет доступна команда если не установлен MinimalGroup иначее - идут обе, идет после проверки минимальной группы
			.setPerm((gamer)->{//Создание индивидуальной проверки, которая идет после проверки мин. группы и onlygroup, например для проверки каких-то других данных кроме группы (return true - может выполнить / false - нет прав)
    			final int level=5;
    			if(gamer.getLevel()>level){
    				gamer.sendMessageLocale("PLAYER_NO_USE_LEVEL",level,Ktem_Group.YOUTUBE.getNameEn());
    				return false;
    			}
    			return true;
    		})
			.setAction((gamer,player,args)->{
				player.sendMessage("Perm command "+Ktem_Group.HELPER.getNameEn()+"§r tested");
			})
			.create();
    	this.newCmd("localization","lang","локализация")//Пример использования
    		.setArgs("getstring","getlist","getforlist")//Дописание аргументов при начале их ввода и нажатии на кнопку TAB, с учетом доступа к команде
    		.blockTab()//Блокировка подбора ников игроков
    		.setAction((gamer,player,args)->{
    			if(args.length==0){
                	player.sendMessage("§fThis a localization tested command");
                	player.sendMessage("§fSee all sub commands from https://github.com/ktemprojectsinc/FlyCore/blob/master/Dev/CorePlugins/TestCorePlugin/TestClass.java");
                	gamer.sendClickMessage("See all keys localization from link §dhttps://github.com/ktemprojectsinc/FlyCore/tree/master/languages",
            				"https://github.com/ktemprojectsinc/FlyCore/tree/master/languages",
            				"Click, to get link",
            				ChatClickable.EnumClickAction.OPEN_URL,
            				ChatClickable.EnumHoverAction.SHOW_TEXT);
                	return;
    			}
    			switch(args[0].toLowerCase()){
    				default : {
    					player.sendMessage("§fSub command "+args[0]+" not found, see https://github.com/ktemprojectsinc/FlyCore/blob/master/Dev/CorePlugins/TestCorePlugin/TestClass.java");
    					return;
    				}
	    			case "getstring" : {
	    				if(args.length==1){
	        				player.sendMessage("§fUse a /localization getstring <key>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet string localization for key %s",args[1]));
	        			try{
	        				gamer.sendMessageLocale(args[1]);
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get string localization key "+args[1]+" error "+e.getMessage());
	        			}
	    				break;
	    			}
	    			case "getlist" : {
	    				if(args.length==1){
	        				player.sendMessage("§fUse a /localization getstring <key>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet string localization for key %s",args[1]));
	        			try{
	        				gamer.sendMessageLocale(args[1]);
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get string localization key "+args[1]+" error "+e.getMessage());
	        			}
	        			break;
	    			}
	    			case "getforlist" : {
	    				if(args.length<=2){
	        				player.sendMessage("§fUse a /localization getforlist <key> <int>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet list for localization for key %s",args[1]));
	        			try{
	        				player.sendMessage(gamer.getLanguageList(args[1]).get(Integer.parseInt(args[2])));
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get list for localization key "+args[1]+" error "+e.getMessage());
	        			}
	        			break;
	    			}
    			}
    		})
    		.create();
	}

}
