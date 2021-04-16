# CraftBlocker
#### About Plugin


This plugin was created for blocking crafting items
#### Features
- Log System
- Bypass Permissions
- API


## API

#### CraftCancel Event

if you want to run code if crafting is cancelled you can use event **CraftCancel**
Example code:
```java
public void onCraftCancel(CraftCancel e) {
   Bukkit.broadcastMessage(e.getPlayer() + " tried to craft " + ChatColor.YELLOW + e.getItem());
} 
```    
#### isMaterialBlocked

Do you want check if material is blocked? you can use code:
```java
CraftBlocker craft = new CraftBlocker();
if(craft.isMaterialBlocked(Material.BEACON)) {  
}
```	      
#### addItemToBlockedItems

If you want add item to blocked items you can use this code:
```java
 CraftBlocker craft = new CraftBlocker();
 craft.addItemToBlockedItems(Material.BEACON);
```	      
#### removeItemFromBlockedItems

If you want to remove item from blocked items you can use this code:
```java
CraftBlocker craft = new CraftBlocker();
craft.removeItemFromBlockedItems(1);
```	    
