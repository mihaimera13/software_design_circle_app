package server.image;

import java.util.HashMap;
import java.util.Map;

public class ProxyImageServer implements ImageServer{
    private RealImageServer realImageServer;
    private Map<String, byte[]> imageCache;
    public ProxyImageServer(){
        imageCache = new HashMap<>();
        realImageServer = new RealImageServer();
    }

    @Override
    public byte[] getImage(String path) {
        if(imageCache.containsKey(path)){
            return imageCache.get(path);
        }
        else{
            byte[] image = realImageServer.getImage(path);
            imageCache.put(path, image);
            return image;
        }
    }
}
