## 基本資訊

*   Java version: 17
*   Spring boot version: 3.2.4

## Requirement

*   jre 17+
*   Docker & Docker compose

## Port

1.  Http Server: 8081/tcp
2.  RSocket Server: 9898/tcp

## 機制

*   透過 TB 原生的 edge key & secret 作為帳號密碼建立連線，若不符會在連線階段拒絕請求。
*   透過修改 edge 內的各設備 profile 設定排程及要播放的樣式 ( style )。
*   edge 透過 RSocket 連線後，center 會將 session object 保存在 memory，等待有任何更新時同步至 edge。
*   檔案刪除後會備份到 backup 資料夾下，暫無定期自動刪除功能。
*   靜態資源上傳後透過 web server host ( NginX etc... )，預設圖片需手動複製到靜態資源存放路徑。
    *   NginX sample config:
        ```
        server {
            listen       8082;
            server_name  localhost;

            #access_log  /var/log/nginx/host.access.log  main;

            location /resource {
                alias /usr/share/nginx/.spx-resource;
            }
        }
        ```

## Setup

1.  TB Web 建立 edge entity, device entity
2.  assign:
    *   dashboard to customer
    *   device to customer
    *   edge to customer
    *   dashboard to edge (從 edge 做 assign 的動作)
    *   device to edge (從 edge 做 assign 的動作)
3.  啟動 server: `docker compose up ds-center -d` 並確認啟動成功
4.  新增 Edge 設定: POST - /api/signage/edge  
    request body example:

```request_body
{
    "id": "EDGE_ID",
    "profiles": [
        {
            "id": "DEVICE_ID",
            "type": "DISPLAY" // 目前 signage type enum 為 DISPLAY, ePAPER，目前版本行為無分別，提供後續串接電子紙時辨別。
        }
    ]
}
```

5.  新增版型: POST - /api/signage/template request body example:

```text
{
    "name": "直式三層",
    "blocksType": {
        "block1": "text",   // 可依需求調整，對應 display widget 中的判斷條件呈現不同內容
        "block2": "media",
        "block3": "media"
    }
},
```

6.  透過 digital signage dashboard 上傳檔案、設定樣式、排程等。