package draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

/**
 * Created by Sergey on 16.01.2017.
 */
public class Drawing {
    GraphicsContext context;

    public Drawing(GraphicsContext context){
        this.context = context;
    }

    public void draw(ImageBuffer image, Handler<Color, Color> handlerColor, Handler<ImageBuffer, ImageBuffer> handlerImage) throws Exception {
        context.fillRect(0,0,context.getCanvas().getWidth(), context.getCanvas().getHeight());
        image = handlerImage.process(image);
        PixelWriter writer = context.getPixelWriter();
        for(int i = 0; i < ((int)context.getCanvas().getWidth()<image.getWidth()?(int)context.getCanvas().getWidth():image.getWidth()); i++)
            for(int j = 0; j < ((int)context.getCanvas().getHeight()<image.getHeight()?(int)context.getCanvas().getHeight():image.getHeight()); j++)
                writer.setColor(i, j, handlerColor.process(image.getPixel(i, j)));
    }
}
