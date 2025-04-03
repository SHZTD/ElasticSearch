import random
from datetime import datetime, timedelta

log_file = "access.log"
ip_addresses = ["192.168.1.1", "172.16.0.1", "10.0.0.1"]
urls = ["/index.html", "/about.html", "/contact.html", "/products.html"]
status_codes = [200, 404, 500]

start_date = datetime(2025, 1, 1)
end_date = datetime(2025, 12, 31)

with open(log_file, "w") as f:
    for _ in range(100000):
        ip = random.choice(ip_addresses)
        url = random.choice(urls)
        status = random.choice(status_codes)
        timestamp = start_date + timedelta(seconds=random.randint(0, int((end_date - start_date).total_seconds())))
        log_entry = f"{ip} - - [{timestamp.strftime('%d/%b/%Y:%H:%M:%S')}] \"GET {url} HTTP/1.1\" {status} 1024\n"
        f.write(log_entry)

print(f"Logs generados y guardados en {log_file}")

