![](https://cdn.glitch.com/fd177e7a-ac8c-4bd5-a685-77913b63a81d%2FCraftBlocker_Title.png?v=1619211839956)

#### About Plugin


This plugin was created for blocking crafting items
#### Features
- Log System
- Bypass Permissions
- API
- bStats Statistics

#### Contributors

- HAHOOS: Programmer, Owner
- dobrekSpryciarz: Graphics

#### Commands
/craftblocker configuration reload - Reload configuration files
/craftblocker configuration everything enable - Enable Crafting Blocker for every item
/craftblocker configuration everything disable - Disable Crafting Blocker for every item
/craftblocker configuration certainItem enable - Enable Crafting Blocker for every itemce
/craftblocker configuration certainItem enable - Enable Crafting Blocker for every item
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
## Statistics

![](http://bstats.org/signatures/bukkit/CraftBlocker.svg)
More Statistics on [bStats](http://bstats.org/plugin/bukkit/CraftBlocker/11114 "bStats")

## Reviews
<details><summary>SHOW</summary>
<p>

#### No one make review :(



</p>
</details>


