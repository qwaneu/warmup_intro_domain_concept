# Warm up exercise introduce domain concept in small steps

In the VendingMachine there are two HashMaps of choice to something

* `choice` to `price`
* `choice` to `can (type)`

It seems that there is a concept missing that combines the `price` and `can`. It could be something like:

* Dispenser / Drawer / Shelf (some concept that holds one type of cans and is able to dispense one)
* Product (some concept that holds the type of product and the price)

Try to introduce a concept like that by changing one line at a time as much as possible.

I.e. DO NOT introduce a class and try to integrate it in one big integration step.
