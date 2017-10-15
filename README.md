# ImageJ_color_selection_plugin

This repository contains several color selection plugins for ImageJ. They all have the same functionalities but differ in the execution. They work as follows :
1) The user chooses a reference color
2) All of the pixels of the image having a color close to the reference are conserved
3) All the other pixel values are replaced by the corresponding greylevel values.

The main variations betwen plugins are about the computation of the distance between the reference color and other pixels' colors. This distance will be computed either in RGB space or in HSB space.
For both choices, the reference color will be chosen in two different ways : fistly by asking the user the RGB values at the start of the plugin, and secondly by using the MouseListener interface to get the pixel value from a click with the mouse.

SelectionCouleur.java uses RGB space and manually entered values.
SelectionCouleur2.java uses HSB space and manually entered values.
SelectionCouleur3.java uses RGB space and mouse click, and creates a new image with each click.
SelectionCouleur4.java uses RGB space and mouse click, and adds selected colors to the same greyscale image with each click.
SelectionCouleur5.java uses HSB space and mouse click, and creates a new image with each click.
SelectionCouleur6.java uses HSB space and mouse click, and adds selected colors to the same greyscale image with each click.
