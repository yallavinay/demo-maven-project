
## 🛠️ Tools Used
| Tool | Purpose |
|------|----------|
| **AWS EC2 (Ubuntu 22.04/24.04)** | Cloud instance hosting Jenkins |
| **Java JDK 11** | Compile and run Java code |
| **Maven** | Build automation tool |
| **Jenkins** | CI/CD automation server |
| **Git & GitHub** | Version control and repository hosting |

---

## 📁 Project Structure
hello-java-maven/
├── pom.xml
└── src/
└── main/
└── java/
└── HelloWorld.java

---

## 💻 Java Source Code

**`src/main/java/HelloWorld.java`**
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Jenkins + Maven on AWS Ubuntu!");
    }
}
POM.XML :

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>hello</artifactId>
  <version>1.0</version>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
Jenkins Setup on AWS Ubuntu
🧩 Step 1: Launch EC2 Instance

Create an Ubuntu Server 22.04/24.04 LTS instance (t2.micro)

Allow these inbound ports:

22 (SSH)

8080 (Jenkins)

80 (optional HTTP)

💻 Step 2: Connect via SSH
chmod 400 aws-key.pem
ssh -i "aws-key.pem" ubuntu@<EC2-Public-IP>

⚙️ Step 3: Install Dependencies
sudo apt update && sudo apt upgrade -y
sudo apt install -y openjdk-11-jdk maven git wget curl unzip

🧱 Step 4: Install Jenkins
curl -fsSL https://pkg.jenkins.io/debian/jenkins.io.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

sudo apt update
sudo apt install -y jenkins
sudo systemctl enable jenkins
sudo systemctl start jenkins


Access Jenkins:

http://<EC2-Public-IP>:8080


Unlock Jenkins:

sudo cat /var/lib/jenkins/secrets/initialAdminPassword


Install Suggested Plugins → Create admin user → Continue.

🧰 Jenkins Configuration

Go to Manage Jenkins → Tools → Global Tool Configuration

Add tools:

JDK:
Name → jdk11, Path → /usr/lib/jvm/java-11-openjdk-amd64

Maven:
Name → maven3, Path → /usr/share/maven

Git:
Path → /usr/bin/git

Save ✅

🧮 Create Jenkins Freestyle Project

New Item → Freestyle Project → Name: hello-java-maven

Source Code Management:

Select Git

Repository URL: https://github.com/<your-username>/hello-java-maven.git

Credentials: Add GitHub username and Personal Access Token (PAT)

Build Step:

Add build step → Invoke top-level Maven targets

Maven Version → maven3

Goals → clean package

Save and click Build Now

✅ Expected Jenkins Output

In Console Output:

[INFO] Scanning for projects...
[INFO] Building hello 1.0
[INFO] BUILD SUCCESS
