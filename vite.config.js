import { defineConfig } from 'vite';
import autoprefixer from 'autoprefixer';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

export default defineConfig(() => {
  
  

  return {
    
    root: path.join(__dirname, 'src', 'main', 'resources'),

    build: {
      outDir: path.join(__dirname, 'target', 'classes', 'static'),
      rollupOptions: {
        input: 'src/main/resources/js/app.js', 
        output: {
          entryFileNames: 'js/bundle.js',
          chunkFileNames: 'js/[name].js',
          assetFileNames: ({ name }) => {
            if (name && name.endsWith('.css')) {
              return 'css/bundle.css';
            }
            return '[ext]/[name].[ext]';
          }
        }
      }
    },

    css: {
      postcss: {
        plugins: [autoprefixer()]
      }
    },

    server: {
      port: 8081,
        proxy: {
          '^/(?!js|css|assets)': {
            target: 'http://localhost:8080',
            changeOrigin: true,
            secure: false
          }
        }
      },
      watch: {
        include: [
          path.join(__dirname, 'src', 'main', 'resources', 'templates', '**/*.html'),
          path.join(__dirname, 'src', 'main', 'resources', 'js', '**/*.js'),
          path.join(__dirname, 'src', 'main', 'resources', 'scss', '**/*.scss'),
        ]
      }
    }
  });
