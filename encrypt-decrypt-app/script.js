class EncryptDecrypt {
  constructor() {
    this.initializeElements()
    this.attachEventListeners()
    this.initializeFeatures()
  }

  initializeElements() {
    this.inputText = document.getElementById("inputText")
    this.encryptionKey = document.getElementById("encryptionKey")
    this.outputText = document.getElementById("outputText")
    this.encryptBtn = document.getElementById("encryptBtn")
    this.decryptBtn = document.getElementById("decryptBtn")
    this.clearBtn = document.getElementById("clearBtn")
    this.copyBtn = document.getElementById("copyBtn")
    this.toggleKey = document.getElementById("toggleKey")
    this.charCount = document.getElementById("charCount")
    this.strengthIndicator = document.getElementById("strengthIndicator")
    this.strengthText = document.getElementById("strengthText")
    this.outputType = document.getElementById("outputType")
    this.outputLength = document.getElementById("outputLength")
    this.statusContainer = document.getElementById("statusContainer")
  }

  attachEventListeners() {
    this.encryptBtn.addEventListener("click", () => this.encryptText())
    this.decryptBtn.addEventListener("click", () => this.decryptText())
    this.clearBtn.addEventListener("click", () => this.clearAll())
    this.copyBtn.addEventListener("click", () => this.copyResult())
    this.toggleKey.addEventListener("click", () => this.toggleKeyVisibility())
    
    this.inputText.addEventListener("input", () => this.updateCharCount())
    
    this.encryptionKey.addEventListener("input", () => this.updateKeyStrength())
    
    this.inputText.addEventListener("keydown", (e) => {
      if (e.ctrlKey && e.key === "Enter") {
        this.encryptText()
      }
    })

    this.encryptionKey.addEventListener("keydown", (e) => {
      if (e.key === "Enter") {
        this.encryptText()
      }
    })
    
    this.outputText.addEventListener("input", () => this.updateOutputInfo())
  }

  initializeFeatures() {
    this.updateCharCount()
    this.updateKeyStrength()
    this.updateOutputInfo()
  }
  
  xorCipher(text, key) {
    if (!key || key.length === 0) {
      throw new Error("Encryption key cannot be empty")
    }

    if (!text || text.length === 0) {
      throw new Error("Text cannot be empty")
    }

    let result = ""
    for (let i = 0; i < text.length; i++) {
      const textChar = text.charCodeAt(i)
      const keyChar = key.charCodeAt(i % key.length)
      result += String.fromCharCode(textChar ^ keyChar)
    }
    return result
  }
  
  toBase64(str) {
    try {
      return btoa(unescape(encodeURIComponent(str)))
    } catch (error) {
      throw new Error("Failed to encode text")
    }
  }

  fromBase64(str) {
    try {
      return decodeURIComponent(escape(atob(str)))
    } catch (error) {
      throw new Error("Invalid encrypted text format")
    }
  }

  validateInputs() {
    const text = this.inputText.value.trim()
    const key = this.encryptionKey.value.trim()

    if (!text) {
      this.showStatus("Please enter some text to encrypt/decrypt", "error")
      this.inputText.focus()
      return false
    }

    if (!key) {
      this.showStatus("Please enter an encryption key", "error")
      this.encryptionKey.focus()
      return false
    }

    if (key.length < 3) {
      this.showStatus("Encryption key should be at least 3 characters long", "error")
      this.encryptionKey.focus()
      return false
    }

    return true
  }

  encryptText() {
    if (!this.validateInputs()) return

    try {
      this.setButtonLoading(this.encryptBtn, true)
      
      setTimeout(() => {
        try {
          const text = this.inputText.value
          const key = this.encryptionKey.value

          const encrypted = this.xorCipher(text, key)
          const base64Encrypted = this.toBase64(encrypted)

          this.outputText.value = base64Encrypted
          this.updateOutputInfo()
          this.showStatus("✅ Text encrypted successfully!", "success")
        } catch (error) {
          this.showStatus("❌ Encryption failed: " + error.message, "error")
        } finally {
          this.setButtonLoading(this.encryptBtn, false)
        }
      }, 300)
    } catch (error) {
      this.showStatus("❌ Encryption failed: " + error.message, "error")
      this.setButtonLoading(this.encryptBtn, false)
    }
  }

  decryptText() {
    if (!this.validateInputs()) return

    try {
      this.setButtonLoading(this.decryptBtn, true)

      setTimeout(() => {
        try {
          const encryptedText = this.inputText.value
          const key = this.encryptionKey.value

          const decodedText = this.fromBase64(encryptedText)
          const decrypted = this.xorCipher(decodedText, key)

          this.outputText.value = decrypted
          this.updateOutputInfo()
          this.showStatus("✅ Text decrypted successfully!", "success")
        } catch (error) {
          this.showStatus("❌ Decryption failed. Please check your encrypted text and key.", "error")
        } finally {
          this.setButtonLoading(this.decryptBtn, false)
        }
      }, 300)
    } catch (error) {
      this.showStatus("❌ Decryption failed: " + error.message, "error")
      this.setButtonLoading(this.decryptBtn, false)
    }
  }

  clearAll() {
    this.inputText.value = ""
    this.encryptionKey.value = ""
    this.outputText.value = ""
    this.updateCharCount()
    this.updateKeyStrength()
    this.updateOutputInfo()
    this.inputText.focus()
    this.showStatus("🗑️ All fields cleared", "success")
  }

  async copyResult() {
    if (!this.outputText.value) {
      this.showStatus("❌ No result to copy", "error")
      return
    }

    try {
      await navigator.clipboard.writeText(this.outputText.value)
      this.showStatus("📋 Result copied to clipboard!", "success")
    } catch (error) {
      this.outputText.select()
      document.execCommand("copy")
      this.showStatus("📋 Result copied to clipboard!", "success")
    }
  }

  toggleKeyVisibility() {
    const isPassword = this.encryptionKey.type === "password"
    this.encryptionKey.type = isPassword ? "text" : "password"
    this.toggleKey.innerHTML = isPassword ? '<span class="eye-icon">🙈</span>' : '<span class="eye-icon">👁️</span>'
  }

  updateCharCount() {
    const count = this.inputText.value.length
    this.charCount.textContent = count.toLocaleString()
  }

  updateKeyStrength() {
    const key = this.encryptionKey.value
    const strength = this.calculateKeyStrength(key)

    this.strengthIndicator.style.width = `${strength.percentage}%`
    this.strengthIndicator.style.backgroundColor = strength.color
    this.strengthText.textContent = strength.text
  }

  calculateKeyStrength(key) {
    if (!key) return { percentage: 0, color: "#e5e7eb", text: "Enter a key" }

    let score = 0
    
    if (key.length >= 8) score += 25
    else if (key.length >= 5) score += 15
    else if (key.length >= 3) score += 10
    
    if (/[a-z]/.test(key)) score += 15
    if (/[A-Z]/.test(key)) score += 15
    if (/[0-9]/.test(key)) score += 15
    if (/[^a-zA-Z0-9]/.test(key)) score += 20
    
    if (key.length >= 12) score += 10

    if (score >= 80) return { percentage: 100, color: "#10b981", text: "Very Strong" }
    if (score >= 60) return { percentage: 80, color: "#059669", text: "Strong" }
    if (score >= 40) return { percentage: 60, color: "#f59e0b", text: "Medium" }
    if (score >= 20) return { percentage: 40, color: "#f97316", text: "Weak" }
    return { percentage: 20, color: "#ef4444", text: "Very Weak" }
  }

  updateOutputInfo() {
    const output = this.outputText.value
    if (!output) {
      this.outputType.textContent = ""
      this.outputLength.textContent = ""
      return
    }
    
    const isBase64 = /^[A-Za-z0-9+/]*={0,2}$/.test(output)
    this.outputType.textContent = isBase64 ? "Encrypted" : "Decrypted"
    this.outputLength.textContent = `${output.length} chars`
  }

  setButtonLoading(button, loading) {
    if (loading) {
      button.disabled = true
      button.style.opacity = "0.7"
      const originalText = button.querySelector(".btn-text").textContent
      button.querySelector(".btn-text").textContent = "Processing..."
      button.dataset.originalText = originalText
    } else {
      button.disabled = false
      button.style.opacity = "1"
      if (button.dataset.originalText) {
        button.querySelector(".btn-text").textContent = button.dataset.originalText
      }
    }
  }

  showStatus(message, type) {
    const statusDiv = document.createElement("div")
    statusDiv.className = `status-message status-${type}`
    statusDiv.innerHTML = `
      <span class="status-icon">${type === "success" ? "✅" : "❌"}</span>
      <span class="status-text">${message}</span>
    `

    this.statusContainer.appendChild(statusDiv)
    
    setTimeout(() => {
      statusDiv.style.animation = "slideOut 0.3s ease forwards"
      setTimeout(() => statusDiv.remove(), 300)
    }, 4000)
  }
}

document.addEventListener("DOMContentLoaded", () => {
  new EncryptDecrypt()
  
  const style = document.createElement("style")
  style.textContent = `
    @keyframes slideOut {
      to {
        transform: translateX(100%);
        opacity: 0;
      }
    }
  `
  document.head.appendChild(style)
})